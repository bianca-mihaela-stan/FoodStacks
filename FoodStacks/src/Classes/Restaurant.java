package Classes;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Restaurant implements Cloneable {
    private String name;
    private List<String> phoneNumbers = new ArrayList<String>();
    private Address address;
    private List<Review> reviews = new ArrayList<Review>();
    private Owner owner;
    private List<Menu> menus = new ArrayList<Menu>();
    private RestaurantType restaurantType;
    protected Long id;
    protected static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    private static AtomicLong restaurantID = new AtomicLong(0);

    private static Long newID()
    {
        return restaurantID.incrementAndGet();
    }

    Restaurant()
    {
        id = newID();
    }

    public Restaurant clone() throws CloneNotSupportedException
    {
        Restaurant restaurant = new Restaurant();
        restaurant.name = name;
        restaurant.address = address.clone(); // Here i'm cloning the address.
        List<String> phone = new ArrayList<>();
        for(var ph : phoneNumbers)
        {
            phone.add(ph);
        }
        restaurant.phoneNumbers=phone;
        List<Review> rev = new ArrayList<Review>();
        for(var review : this.reviews)
        {
            rev.add(review.clone());
        }
        restaurant.reviews=rev;
        restaurant.owner = owner.clone();
        List<Menu> men = new ArrayList<>();
        for(var m : menus)
        {
            men.add(m.clone());
        }
        restaurant.menus=men;
        restaurant.restaurantType = restaurantType;
        return restaurant;
    }


    public static class Builder{
        private Restaurant restaurant = new Restaurant();

        public Builder(Owner owner, Address address, String name){
            restaurant.owner = owner;
            var restaurants = owner.getRestaurants();
            restaurants.add(this.restaurant);
            owner.setRestaurants(restaurants);
            restaurant.address = address;
            restaurant.name =name;
        }

        public Builder(Address address, String name){
            restaurant.address = address;
            restaurant.name =name;
        }
        public Restaurant.Builder withPhoneNumber(String phoneNumber){
            restaurant.phoneNumbers.add(phoneNumber);
            return this;
        }
        public Restaurant.Builder withMenu(Menu menu){
            restaurant.menus.add(menu);
            return this;
        }
        public Restaurant.Builder withRestaurantType(RestaurantType restaurantType)
        {
            restaurant.restaurantType=restaurantType;
            return this;
        }

        public Restaurant build()
        {
            return this.restaurant;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumber) {
        this.phoneNumbers = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menu) {
        this.menus = menu;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public static ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static void setRestaurants(ArrayList<Restaurant> restaurants) {
        Restaurant.restaurants = restaurants;
    }

    public Double getPriceForDish(Dish dish)
    {
        for( Menu menu : menus)
        {
            Double price = menu.getPrice(dish);
            if(price!=null)
                return price;
        }
        return null;
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", address=" + address +
                ", reviews=" + reviews +
                ", owner=" + owner +
                ", menus=" + menus +
                ", restaurantType=" + restaurantType +
                ", id=" + id +
                '}';
    }
}
