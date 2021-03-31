package Classes;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart implements Cloneable{
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

    public Cart clone() throws CloneNotSupportedException
    {
        Cart cart = new Cart();
        cart.price = price;
        var dishes = new HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>>();
        for(var restaurant : this.getDishes().keySet())
        {
            List<Triplet<Dish, Integer, Double>> list = new ArrayList<Triplet<Dish, Integer, Double>>();
            for(var dish : this.getDishes().get(restaurant))
            {
                list.add(new Triplet<Dish, Integer, Double>(dish.getValue0().clone(), dish.getValue1(), dish.getValue2()));
            }
            dishes.put(restaurant.clone(), list);
        }
        cart.dishes=dishes;
        return cart;
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
