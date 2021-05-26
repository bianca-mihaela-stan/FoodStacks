package Mains;

import Database.*;
import Classes.*;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class DatabasePhase {
    public static void print(Object x)
    {
        System.out.println(x);
    }

    public static void main(String[] args) throws ClassNotFoundException {
////        Client client = new Client("Andrei Stanescu", "andrei.stanescu@gmail.com", "andrei1234", "0712345678");
//        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
//        var clients = userDatabaseManager.getAllClients();
//        Address address1 = new Address("HCC", "25");
//        Address address2 = new Address("HCC", "19B");
//        AddressDatabaseManager addressDatabaseManager = new AddressDatabaseManager();
//        addressDatabaseManager.insert(address1);
//        addressDatabaseManager.insert(address2);
////        List<Address> addresses = new ArrayList<>();
//        addresses = addressDatabaseManager.getAllAddresses();
//        print(addresses);

//        Owner owner1 = new Owner.Builder( "andreea.georgescu@gmail.com", "andreea1234").withName("Andreea Georgescu").withPhoneNumber("0715216521").build();
//        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
//        var owners = userDatabaseManager.getAllQwners();
//        var clients = userDatabaseManager.getAllClients();
//        print(owners);

        DatabaseManager databaseManager = new DatabaseManager();
//        var owners = databaseManager.getAllQwners();
        var dishes = databaseManager.getAllDishes();
        var clients = databaseManager.getAllClients();
//        var restaurants = databaseManager.getAllRestaurants();
//        var addresses = databaseManager.getAllAddresses();
//        List<Triplet<String, Double, String>> recipe = new ArrayList<>();
//        recipe.add(new Triplet<String, Double, String>("water", 100.0, "ml"));
//        Dish dish1 = new Dish.Builder("sparkling water").withIngredient("water", 150., "ml").build();
//        databaseManager.insertDish(dish1);
//        dishes= databaseManager.getAllDishes();
////        print(dishes);
//
        var restaurants = databaseManager.getAllRestaurants();
//        print(restaurants);
//        Menu menu = new Menu.Builder("drinks").withElement(dishes.get(0), 10.7).withElement(dishes.get(5), 19.4).withRestaurant(restaurants.get(0)).withId(1L).build();
//        databaseManager.insertMenu(menu);
        var menus = databaseManager.getAllMenus();


//        databaseManager.getAllOrders();
//        print(clients);
//        print(restaurants);
//        print(restaurants.get(0));
//        Order order = new Order.Builder(restaurants.get(0), clients.get(0)).withDish(dishes.get(0), 2, 10.4).withId(1L).build();
//        databaseManager.insertOrder(order);
//        print(databaseManager.getAllOrders());
//        print(menus);

        databaseManager.deleteAddress(6L);
    }
}
