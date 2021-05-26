package Classes;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Triplet;
import org.javatuples.Pair;

public class Order{
    protected LocalDate date;
    protected List<Triplet<Dish, Integer, Double>> dishesOrdered = new ArrayList<Triplet<Dish, Integer, Double>>();
    protected Double finalPrice = 0.0;
    protected Restaurant restaurant;
    protected Client user;
    protected Long id;


    protected static AtomicLong userID = new AtomicLong(0);

    protected static Long newID()
    {
        return userID.incrementAndGet();
    }

    public Order()
    {
        this.date = LocalDate.now();
        this.id=newID();
    }
    Order(LocalDate date, List<Triplet<Dish, Integer, Double>> dishesOrdered,
          Double finalPrice, Restaurant restaurant, Client user)
    {
        this.date=date;
        this.dishesOrdered=dishesOrdered;
        this.finalPrice=finalPrice;
        this.restaurant=restaurant;
        this.user=user;
        var orders = user.getOrders();
        orders.add(this);
        user.setOrders(orders);
        this.id=newID();
        this.sortDishes();
    }

    public static class Builder{
        protected Order order = new Order();


        public Builder(Restaurant restaurant, Client user){
            order.restaurant = restaurant;
            order.user = user;
            var orders = order.user.getOrders();
            orders.add(order);
            order.user.setOrders(orders);
        }
        public Order.Builder withDish(Dish dish, Double price){
            Triplet<Dish, Integer, Double> tr= new Triplet<Dish, Integer, Double> (dish, 1, price);
            order.dishesOrdered.add(tr);
            order.finalPrice+=price;
            return this;
        }
        public Order.Builder withDish(Dish dish, Integer number, Double price){
            Triplet<Dish, Integer, Double> tr= new Triplet<Dish, Integer, Double> (dish, number, price);
            order.dishesOrdered.add(tr);
            order.finalPrice+=price*number;
            return this;
        }
        public Order.Builder withDishes(List<Pair<Dish, Integer>> dishes){
            for (Pair<Dish, Integer> dish : dishes)
            {
                Triplet<Dish, Integer, Double> tr= new Triplet<Dish, Integer, Double> (dish.getValue0(), dish.getValue1(), null);
                order.dishesOrdered.add(tr);
            }
            return this;
        }

        public Order build()
        {
            for(Triplet<Dish, Integer, Double> elem : order.dishesOrdered)
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
        String output = "Delivery{" +
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Triplet<Dish, Integer, Double>> getDishesOrdered() {
        this.sortDishes();
        return dishesOrdered;
    }

    public void setDishesOrdered(List<Triplet<Dish, Integer, Double>> dishesOrdered) {
        this.dishesOrdered = dishesOrdered;
        this.sortDishes();
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    protected void sortDishes()
    {
        dishesOrdered.sort(new Comparator<Triplet<Dish, Integer, Double>>() {
            @Override
            public int compare(Triplet<Dish, Integer, Double> o1, Triplet<Dish, Integer, Double> o2) {
                if(o1.getValue0().getName().compareTo(o2.getValue0().getName()) < 0){
                    return 1;
                }
                return 0;
            }
        });
    }
}
