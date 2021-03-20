package Classes;

import java.time.LocalDate;
import java.util.*;

public class Driver extends User {
    private String cnp;
    List<Review> reviews;
    Hashtable<LocalDate, Delivery> deliveries;

    @Override
    public String toString() {
        return "Driver{" +
                "cnp='" + cnp + '\'' +
                ", reviews=" + reviews +
                ", deliveries=" + deliveries +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Hashtable<LocalDate, Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Hashtable<LocalDate, Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }
}
