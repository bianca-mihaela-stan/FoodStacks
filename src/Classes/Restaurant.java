package Classes;

import java.util.*;

public class Restaurant {
    private String name;
    private String phoneNumber;
    private Address address;
    private List<Review> reviews;
    private Owner owner;
    private Menu menu;

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", reviews=" + reviews +
                ", owner=" + owner +
                ", menu=" + menu +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Float getPriceForDish(Dish dish)
    {
        return menu.getPrice(dish);
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }

}
