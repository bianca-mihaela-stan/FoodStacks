package Classes;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Pair;

public class Client extends User {
    private List<Order> orders= new ArrayList<Order>();
    private HashMap<AddressIdentifier, Address> addresses = new HashMap<AddressIdentifier, Address>();
    private HashMap<RestaurantType, List<Restaurant>> favourites = new HashMap<RestaurantType, List<Restaurant>>();
    private Cart cart;

    public Client()
    {
        super();
    }
    public Client(String name, String surname, String username,
                String email, String password, String phoneNumber){
        super(name, surname, username, email, password, phoneNumber);
    }

    public static class Builder {

        private Client client = new Client();

        public Client build() {
            return this.client;
        }

        public Builder(String email, String password) {
            client.email = email;
            client.password = password;
        }

        public Client.Builder withUsername(String username) {
            client.username = username;
            return this;
        }

        public Client.Builder withName(String name) {
            client.name = name;
            return this;
        }

        public Client.Builder withSurname(String surname) {
            client.surname = surname;
            return this;
        }

        public Client.Builder withPhoneNumber(String phoneNumber) {
            client.phoneNumber = phoneNumber;
            return this;
        }

        public Client.Builder withOrders(List<Order> orders){
            client.orders = orders;
            return this;
        }

        public Client.Builder withAddresses(HashMap<AddressIdentifier, Address> addresses)
        {
            client.addresses=addresses;
            return this;
        }

        public Client.Builder withFavourites(HashMap<RestaurantType, List<Restaurant>> favourites)
        {
            client.favourites = favourites;
            return this;
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public HashMap<AddressIdentifier, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(HashMap<AddressIdentifier, Address> addresses) {
        this.addresses = addresses;
    }

    public HashMap<RestaurantType, List<Restaurant>> getFavourites() {
        return favourites;
    }

    public void setFavourites(HashMap<RestaurantType, List<Restaurant>> favourites) {
        this.favourites = favourites;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Client{" +
                "orders=" + orders +
                ", addresses=" + addresses +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                '}';
    }
}
