package Classes;

import org.javatuples.Pair;

import java.util.*;

public class Owner extends User{
    List<Restaurant> restaurants= new ArrayList<Restaurant>();

    public Owner()
    {
        super();
    }
    public Owner(String name, String surname, String username,
                  String email, String password, String phoneNumber){
        super(name, surname, username, email, password, phoneNumber);
    }

    public static class Builder {

        private Owner Owner = new Owner();

        public Owner build() {
            return this.Owner;
        }

        public Builder(String email, String password) {
            Owner.email = email;
            Owner.password = password;
        }

        public Owner.Builder withUsername(String username) {
            Owner.username = username;
            return this;
        }

        public Owner.Builder withName(String name) {
            Owner.name = name;
            return this;
        }

        public Owner.Builder withSurname(String surname) {
            Owner.surname = surname;
            return this;
        }

        public Owner.Builder withPhoneNumber(String phoneNumber) {
            Owner.phoneNumber = phoneNumber;
            return this;
        }

        public Owner.Builder withRestaurants(List<Restaurant> restaurants)
        {
            Owner.restaurants=restaurants;
            return this;
        }
    }

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
                ", id=" + id +
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
