package Classes;

import Functionalities.PlatformService;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Driver extends User{
    private String cnp;
    private List<Review> reviews = new ArrayList<Review>();
    private Hashtable<LocalDate, List<Delivery>> deliveries = new Hashtable<LocalDate, List<Delivery>>();
    private static List<Driver> drivers = new ArrayList<Driver>();

    Driver()
    {
        id = newID();
        drivers.add(this);
    }

    Driver(User user)
    {
        name = user.name;
        surname = user.surname;
        username = user.username;
        email = user.email;
        password = user.password;
        phoneNumber = user.phoneNumber;
        id = newID();
        drivers.add(this);
    }

    public static class Builder{
        private Driver user = new Driver();

        public Builder(String email, String password){
            user.email = email;
            user.password=password;
        }
        public Driver.Builder withName(String name){
            user.name=name;
            return this;
        }
        public Driver.Builder withSurname(String surname){
            user.surname=surname;
            return this;
        }
        public Driver.Builder withEmail (String email){
            user.email=email;
            return this;
        }
        public Driver.Builder withUsername (String username){
            user.username=username;
            return this;
        }
        public Driver.Builder withPhoneNumber (String phoneNumber){
            user.phoneNumber=phoneNumber;
            return this;
        }

        public Driver build()
        {
            this.user.id=newID();
            return this.user;
        }
    }

    @Override
    public String toString() {
        return "Driver{" +
                "cnp='" + cnp + '\'' +
                ", reviews=" + reviews +
//                ", deliveries=" + deliveries +
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

    public Hashtable<LocalDate, List<Delivery>> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Hashtable<LocalDate, List<Delivery>> deliveries) {
        this.deliveries = deliveries;
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }

    public static List<Driver> getDrivers() {
        return drivers;
    }

    public static void setDrivers(List<Driver> drivers) {
        Driver.drivers = drivers;
    }
}
