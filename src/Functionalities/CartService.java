package Functionalities;

import Classes.Cart;
import Classes.Client;
import Classes.Dish;
import Classes.Restaurant;
import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.List;

public class CartService extends PlatformService {

    private static CartService instance;

    private CartService()
    {

    }


    public static CartService getInstance()
    {
        if(instance==null)
        {
            instance = new CartService();
        }
        return instance;
    }
    
    public void removeFromCart(Restaurant restaurant, Dish dish)
    {
        if(loggedInUser instanceof Client)
        {
            var cart = ((Client) loggedInUser).getCart();
            HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>> dishes = cart.getDishes();
            List<Triplet<Dish, Integer, Double>> dishesFromRestaurant = dishes.get(restaurant);
            if(dishesFromRestaurant != null)
                for (Triplet<Dish, Integer, Double> triplet : dishesFromRestaurant) {
                    if (dish.equals(triplet.getValue0())) {
                        dishesFromRestaurant.remove(triplet);
                        break;
                    }
                }
            dishes.put(restaurant, dishesFromRestaurant);
            cart.setDishes(dishes);
            ((Client) loggedInUser).setCart(cart);
            System.out.println("Dish "+ dish + " was successfully removed from cart!");
        }
    }


    public void increaseNumberFromCart(Cart cart, Restaurant restaurant, Dish dish, Integer by)
    {
        if(loggedInUser instanceof Client)
        {
            HashMap<Restaurant, List<Triplet<Dish, Integer, Double>>> dishes = cart.getDishes();
            List<Triplet<Dish, Integer, Double>> dishesFromRestaurant = dishes.get(restaurant);
            for(Triplet<Dish, Integer, Double> triplet : dishesFromRestaurant)
            {
                if(dish.equals(triplet.getValue0()))
                {
                    triplet=triplet.setAt1(triplet.getValue1()-by);
                    if(triplet.getValue1()<=0)
                        dishesFromRestaurant.remove(triplet);
                    break;
                }
            }
            dishes.put(restaurant, dishesFromRestaurant);
            cart.setDishes(dishes);
            System.out.println(by + "portions of "+ dish + " were successfully removed from cart!");
        }
    }
}
