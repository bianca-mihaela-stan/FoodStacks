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
        DatabaseManager databaseManager = new DatabaseManager();
        var addresses = databaseManager.getAllAddresses();
        var clients = databaseManager.getAllClients();
        var dishes = databaseManager.getAllDishes();
        var menus = databaseManager.getAllMenus();
        var orders = databaseManager.getAllOrders();
        var owners = databaseManager.getAllOwners();
        var reviews = databaseManager.getAllReviews();
        var restaurants = databaseManager.getAllRestaurants();

        Dish dish1 = new Dish.Builder("sparkling water").withIngredient("water", 150., "ml").build();
        databaseManager.insertDish(dish1);

        Menu menu = new Menu.Builder("drinks").withElement(dishes.get(0), 10.7).withElement(dishes.get(5), 19.4).withRestaurant(restaurants.get(0)).withId(21L).build();
        databaseManager.insertMenu(menu);

        databaseManager.deleteMenu(1L);

        databaseManager.deleteAddress(6L);
    }
}
