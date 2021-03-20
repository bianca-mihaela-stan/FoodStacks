package Classes;

import java.util.*;
import org.javatuples.Pair;

public class Client extends User {
    private List<Order> orders;
    private List<Pair<Address, AddressIdentifier>> addresses;

    public Client()
    {
        super();
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
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Pair<Address, AddressIdentifier>> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Pair<Address, AddressIdentifier>> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString()
    {
        return "Client{"+
                "name='"+name+'\''+
                ", surname="+surname+'\''+
                ", email='"+email+'\''+
                ", username="+username+'\''+
                ", password="+password+'\''+
                ", phoneNumber="+phoneNumber+
                '}';
    }

}
