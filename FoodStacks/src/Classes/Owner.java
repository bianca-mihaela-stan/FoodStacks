package Classes;

import org.javatuples.Pair;

import java.util.*;

public class Owner extends User{
    List<Restaurant> restaurants= new ArrayList<Restaurant>();
    protected static Map<String, Owner> ownersByEmail = new Hashtable<String, Owner>();

    public Owner clone() throws CloneNotSupportedException
    {
        Owner owner = (Owner) super.clone();
        List<Restaurant> rest = new ArrayList<>();
        for(var r : this.restaurants)
        {
            rest.add(r.clone());
        }
        owner.restaurants=rest;
        return owner;
    }

    public Owner()
    {
        super();
    }
    public Owner(String name, String surname, String username,
                  String email, String password, String phoneNumber){
        super(name, surname, username, email, password, phoneNumber);
    }

    public static class Builder {

        private Owner owner = new Owner();

        public Owner build() {
            return this.owner;
        }

        public Builder(String email, String password) {
            owner.email = email;
            owner.password = password;
            var owners = getOwnersByEmail();
            owners.put(email, this.owner);
            setOwnersByEmail(owners);
        }

        public Owner.Builder withUsername(String username) {
            owner.username = username;
            return this;
        }

        public Owner.Builder withName(String name) {
            owner.name = name;
            return this;
        }

        public Owner.Builder withSurname(String surname) {
            owner.surname = surname;
            return this;
        }

        public Owner.Builder withPhoneNumber(String phoneNumber) {
            owner.phoneNumber = phoneNumber;
            return this;
        }

        public Owner.Builder withRestaurants(List<Restaurant> restaurants)
        {
            owner.restaurants=restaurants;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Owner{" +
//                "restaurants=" + restaurants +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
//                ", id=" + id +
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

    public static Map<String, Owner> getOwnersByEmail() {
        return ownersByEmail;
    }

    public static void setOwnersByEmail(Map<String, Owner> ownersByEmail) {
        Owner.ownersByEmail = ownersByEmail;
    }
}
