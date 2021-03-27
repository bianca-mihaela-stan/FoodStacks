package Classes;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Restaurant {
    private String name;
    private List<String> phoneNumbers = new ArrayList<String>();
    private Address address;
    private List<Review> reviews = new ArrayList<Review>();
    private Owner owner;
    private List<Menu> menus = new ArrayList<Menu>();
    private RestaurantType restaurantType;
    protected Long id;

    private static AtomicLong restaurantID = new AtomicLong(0);

    private static Long newID()
    {
        return restaurantID.incrementAndGet();
    }

    Restaurant()
    {
        id = newID();
    }


    public static class Builder{
        private Restaurant restaurant = new Restaurant();

        public Builder(Owner owner, Address address, String name){
            restaurant.owner = owner;
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

    public Float getPriceForDish(Dish dish)
    {
        for( Menu menu : menus)
        {
            Float price = menu.getPrice(dish);
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
