package Classes;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Pair;

public class Client extends User {
    private List<Order> orders= new ArrayList<Order>();
    private HashMap<AddressIdentifier, Address> addresses = new HashMap<AddressIdentifier, Address>();
    private HashMap<RestaurantType, List<Restaurant>> favourites = new HashMap<RestaurantType, List<Restaurant>>();
    private Cart cart;
    protected static Map<String, Client> clientsByEmail = new Hashtable<String, Client>();

    public Client()
    {
        super();
        cart = new Cart();
    }
    public Client(String name, String surname, String username,
                String email, String password, String phoneNumber){
        super(name, surname, username, email, password, phoneNumber);
    }

    public Client(User user)
    {
        super(user.name, user.surname, user.username, user.email, user.password, user.phoneNumber);
    }
    public void addAddress(Address address, AddressIdentifier addressIdentifier)
    {
        if (!addresses.containsKey(addressIdentifier))
            addresses.put(addressIdentifier, address);
    }

//    public Client(Client otherClient) {
//        name = otherClient.name;
//        surname = otherClient.surname;
//        username = otherClient.username;
//        email = otherClient.email;
//        password = otherClient.password;
//        phoneNumber = otherClient.phoneNumber;
//        id = newID();
//        orders = otherClient.orders;
//        addresses= otherClient.addresses;
//        favourites = otherClient.favourites;
//        cart = otherClient.cart;
//    }

    public static class Builder {

        private Client client = new Client();

        public Client build()
        {
            this.client.cart = new Cart();
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

    public static Map<String, Client> getClientsByEmail() {
        return clientsByEmail;
    }

    public static void setClientsByEmail(Map<String, Client> clientsByEmail) {
        Client.clientsByEmail = clientsByEmail;
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
