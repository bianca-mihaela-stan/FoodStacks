package Classes;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Triplet;
import org.javatuples.Pair;

public class Order {
    protected LocalDate date;
    protected List<Triplet<Dish, Integer, Float>> dishesOrdered;
    protected Float finalPrice;
    protected Restaurant restaurant;
    protected User user;
    protected Long id;


    protected static AtomicLong userID = new AtomicLong(0);

    protected static Long newID()
    {
        return userID.incrementAndGet();
    }

    Order()
    {
        this.date = LocalDate.now();
        this.id=newID();
    }
    Order(LocalDate date, List<Triplet<Dish, Integer, Float>> dishesOrdered,
          Float finalPrice, Restaurant restaurant, User user)
    {
        this.date=date;
        this.dishesOrdered=dishesOrdered;
        this.finalPrice=finalPrice;
        this.restaurant=restaurant;
        this.user=user;
        this.id=newID();
        this.sortDishes();
    }

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
        this.sortDishes();
        return dishesOrdered;
    }

    public void setDishesOrdered(List<Triplet<Dish, Integer, Float>> dishesOrdered) {
        this.dishesOrdered = dishesOrdered;
        this.sortDishes();
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

    protected void sortDishes()
    {
        dishesOrdered.sort(new Comparator<Triplet<Dish, Integer, Float>>() {
            @Override
            public int compare(Triplet<Dish, Integer, Float> o1, Triplet<Dish, Integer, Float> o2) {
                if(o1.getValue0().getName().compareTo(o2.getValue0().getName()) < 0){
                    return 1;
                }
                return 0;
            }
        });
    }
}
