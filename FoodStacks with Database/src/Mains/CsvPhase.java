package Mains;

import Classes.Address;
import Classes.Cart;
import Classes.Client;
import Classes.Dish;
import Functionalities.AddressService;
import Functionalities.CartService;
import Functionalities.ClientService;
import Functionalities.DishService;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CsvPhase {
    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "F:\\GitHub\\FoodStacks\\FoodStacks with Database\\src\\Data\\Address.csv";
        List<Address> addresses = new CsvToBeanBuilder(new FileReader(fileName))
            .withType(Address.class)
            .build()
            .parse();
        addresses.forEach(System.out::println);

        fileName = "F:\\GitHub\\FoodStacks\\FoodStacks with Database\\src\\Data\\Cart.csv";
        List<Cart> carts = new CsvToBeanBuilder(new FileReader(fileName))
            .withType(Cart.class)
            .build()
            .parse();
        carts.forEach(System.out::println);

        fileName = "F:\\GitHub\\FoodStacks\\FoodStacks with Database\\src\\Data\\Dish.csv";
        List<Dish> dishes = new CsvToBeanBuilder(new FileReader(fileName))
            .withType(Dish.class)
            .build()
            .parse();
        dishes.forEach(System.out::println);

        fileName = "F:\\GitHub\\FoodStacks\\FoodStacks with Database\\src\\Data\\Client.csv";
        List<Client> clients = new CsvToBeanBuilder(new FileReader(fileName))
            .withType(Client.class)
            .build()
            .parse();
        clients.forEach(System.out::println);

        System.out.println(CartService.getCarts());
        System.out.println(AddressService.getAddresses());
        System.out.println(DishService.getDishes());
        System.out.println(ClientService.getClients());
    }
}
