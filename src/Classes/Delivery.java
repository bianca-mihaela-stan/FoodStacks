package Classes;

import java.util.*;

public class Delivery extends Order{
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "address=" + address +
                ", date=" + date +
                ", dishesOrdered=" + dishesOrdered +
                ", finalPrice=" + finalPrice +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }


}
