package Classes;

import java.util.*;

public class Owner extends User{
    List<Restaurant> restaurants;

    @Override
    public String toString() {
        return "Owner{" +
                "restaurants=" + restaurants +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant)
    {
        restaurants.add(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant)
    {
        restaurants.remove(restaurant);
    }
}
