package Classes;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.time.LocalDate;
import java.util.*;

public class Delivery extends Order{
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Builder{
        protected Delivery delivery = new Delivery();

        public Builder(Restaurant restaurant, Client user, Address address){
            delivery.restaurant = restaurant;
            delivery.user = user;
            System.out.println(user);
            delivery.address=address;
        }
        public Delivery.Builder withDish(Dish dish, Double price){
            Triplet<Dish, Integer, Double> tr= new Triplet<Dish, Integer, Double> (dish, 1, price);
            delivery.dishesOrdered.add(tr);
            delivery.finalPrice+=price;
            return this;
        }
        public Delivery.Builder withDish(Dish dish, Integer number, Double price){
            Triplet<Dish, Integer, Double> tr= new Triplet<Dish, Integer, Double> (dish, number, price);
            delivery.dishesOrdered.add(tr);
            delivery.finalPrice+=price*number;
            return this;
        }
        public Delivery.Builder withDishes(List<Pair<Dish, Integer>> dishes){
            for (Pair<Dish, Integer> dish : dishes)
            {
                Triplet<Dish, Integer, Double> tr= new Triplet<Dish, Integer, Double> (dish.getValue0(), dish.getValue1(), null);
                delivery.dishesOrdered.add(tr);
            }
            return this;
        }

        public Delivery build()
        {
            for(Triplet<Dish, Integer, Double> elem : delivery.dishesOrdered)
            {
                if(delivery.restaurant.getPriceForDish(elem.getValue0())!=null)
                {
                    delivery.finalPrice+=delivery.restaurant.getPriceForDish(elem.getValue0())*elem.getValue1();
                }
            }
            delivery.date=LocalDate.now();
            return this.delivery;
        }
    }

    @Override
    public String toString() {
        String output = "Delivery{" +
                "address=" + address +
                ", date=" + date + ", dishesDelivered=";
        for(var dish : dishesOrdered)
        {
            output+="("+dish.getValue0().getName()+", "+ dish.getValue1() + ", "+dish.getValue2()+ ")";
        }
        output+= ", finalPrice=" + finalPrice +
                ", restaurant=" + restaurant.getName() +
                ", user=" + user.getEmail() +
                '}';
        return output;
    }


}
