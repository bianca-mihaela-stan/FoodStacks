package Classes;

import java.time.LocalDate;
import java.util.*;
import org.javatuples.Triplet;
import org.javatuples.Pair;

public class Order {
    protected LocalDate date;
    protected List<Triplet<Dish, Integer, Float>> dishesOrdered;
    protected Float finalPrice;
    protected Restaurant restaurant;
    protected User user;

    public static class Builder{
        private Order order = new Order();

        public Builder(Restaurant restaurant, User user){
            order.restaurant = restaurant;
            order.user = user;
        }
        public Order.Builder withDish(Dish dish){
            Triplet<Dish, Integer, Float> tr= new Triplet<Dish, Integer, Float> (dish, 1, null);
            order.dishesOrdered.add(tr);
            return this;
        }
        public Order.Builder withDish(Dish dish, Integer number){
            Triplet<Dish, Integer, Float> tr= new Triplet<Dish, Integer, Float> (dish, number, null);
            order.dishesOrdered.add(tr);
            return this;
        }
        public Order.Builder withDishes(List<Pair<Dish, Integer>> dishes){
            for (Pair<Dish, Integer> dish : dishes)
            {
                Triplet<Dish, Integer, Float> tr= new Triplet<Dish, Integer, Float> (dish.getValue0(), dish.getValue1(), null);
                order.dishesOrdered.add(tr);
            }
            return this;
        }

        public Order build()
        {
            for(Triplet<Dish, Integer, Float> elem : order.dishesOrdered)
            {
                if(order.restaurant.getPriceForDish(elem.getValue0())!=null)
                {
                    order.finalPrice+=order.restaurant.getPriceForDish(elem.getValue0())*elem.getValue1();
                }
            }
            order.date=LocalDate.now();
            return this.order;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", dishesOrdered=" + dishesOrdered +
                ", finalPrice=" + finalPrice +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Triplet<Dish, Integer, Float>> getDishesOrdered() {
        return dishesOrdered;
    }

    public void setDishesOrdered(List<Triplet<Dish, Integer, Float>> dishesOrdered) {
        this.dishesOrdered = dishesOrdered;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setPortionsForDish(Dish dish, Integer number)
    {
        for(Triplet<Dish, Integer, Float> elem : dishesOrdered)
        {
            if(dish==elem.getValue0())
            {
                finalPrice-=elem.getValue1()*elem.getValue2();
                elem.setAt0(number);
                finalPrice+=elem.getValue1()*number;
            }
        }
    }
    public void removeDish(Dish dish)
    {
        for(Triplet<Dish, Integer, Float> elem : dishesOrdered)
        {
            if(dish==elem.getValue0())
            {
                finalPrice-=elem.getValue1()*elem.getValue2();
                dishesOrdered.remove(elem);
            }
        }
    }
}
