package Classes;

import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.List;

public class Cart {
    protected HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>> dishes = new HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>>();
    protected Double price;

    public Cart ()
    {
        price = 0.0;
    }

    public Cart(HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>> dishes)
    {
        this.dishes=dishes;
        this.price=0.0;
        for(Restaurant restaurant : dishes.keySet())
        {
            for(Triplet<Dish, Integer, Double> triplet : dishes.get(restaurant))
            {
                this.price+=triplet.getValue2();
            }
        }
    }

    public HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>> getDishes() {
        return dishes;
    }

    public void setDishes(HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>> dishes) {
        this.dishes = dishes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String output = "Cart-{dishes={";
        for(var dish : dishes.keySet())
        {
            output+=dish.getName()+" =[";
            output+=dishes.get(dish) + ", ";
        }
        output+="]";
        output+="price="+price+ "}";
        return output;
    }
}
