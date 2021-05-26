//package Classes;
//
//import org.javatuples.Triplet;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class Delivery extends Order{
//    private Address address;
//    private LocalDate timeDelivered;
//
//    public Delivery() {
//    }
//
//    public Delivery(LocalDate time_ordered, Client client_id, Double final_price, Restaurant restaurant, List<Triplet<Dish, Integer, Double>> dishesOrdered, Address address, LocalDate timeDelivered) {
//        super(time_ordered, client_id, final_price, restaurant, dishesOrdered);
//        this.address = address;
//        this.timeDelivered = timeDelivered;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public LocalDate getTimeDelivered() {
//        return timeDelivered;
//    }
//
//    public void setTimeDelivered(LocalDate timeDelivered) {
//        this.timeDelivered = timeDelivered;
//    }
//
//    @Override
//    public String toString() {
//        return "Delivery{" +
//            "address=" + address +
//            ", timeDelivered=" + timeDelivered +
//            ", id=" + id +
//            ", time_ordered=" + time_ordered +
//            ", client_id=" + client +
//            ", final_price=" + finalPrice +
//            ", restaurant=" + restaurant +
//            ", dishesOrdered=" + dishesOrdered +
//            '}';
//    }
//}
