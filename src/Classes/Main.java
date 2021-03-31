package Classes;


//import Functionalities.OwnerService;
//import Functionalities.PlatformService;
//import Functionalities.RestaurantService;
import Functionalities.*;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.*;
import static java.lang.String.valueOf;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        PlatformService platformService = PlatformService.getInstance();
        AddressService addressService = AddressService.getInstance();
        CartService cartService = CartService.getInstance();
        ClientService clientService = ClientService.getInstance();
        DeliveryService deliveryService = DeliveryService.getInstance();
        DishService dishService = DishService.getInstance();
        DriverService driverService= DriverService.getInstance();
        MenuService menuService = MenuService.getInstance();
        OrderService orderService = OrderService.getInstance();
        OwnerService ownerService = OwnerService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();


        // Creating 3 addresses.
        Address address1 = new Address.Builder(County.Dambovita, "Targoviste", "A", "25A").build();
        Address address2 = new Address.Builder(County.Bucuresti, "Bucuresti", "Mar", "105A").withSector(1).build();
        Address address3 = new Address.Builder(County.Brasov, "Brasov", "Raton", "101").withBlock("19").withFloor(7).build();

        // Creating 4 dishes.
        Dish dish1 = new Dish.Builder("soup").withIngredient("carrot", 50, Quantity.g).withIngredient("water", 400, Quantity.ml).build();
        Dish dish2 = new Dish.Builder("cake").withIngredient("chocolate", 100, Quantity.g).withIngredient("cream", 100, Quantity.g).build();
        Dish dish3 = new Dish.Builder("fries").withIngredient("carrots", 200, Quantity.g).withIngredient("spices", 5, Quantity.g).build();
        Dish dish4 = new Dish.Builder("Pina Colada").withIngredient("alcohol", 20, Quantity.ml).withIngredient("cream", 200, Quantity.ml).build();
        Dish dish5 = new Dish.Builder("Cappuccino").withIngredient("coffee", 200, Quantity.ml).withIngredient("cream", 200, Quantity.ml).build();

        // Creating 2 menus.
        Menu menu1 = new Menu.Builder("Drinks").withCategory("Alcoholic")
                .withCategory("Nonalcoholic")
                .withCategory("Coffee")
                    .withElement("Alcoholic", dish4, 20.0).build();
        Menu menu2 = new Menu.Builder("Food")
                .withCategory("Soups")
                    .withElement("Soups", dish1, 15.5)
                .withCategory("Sides")
                    .withElement("Sides", dish3, 9.9)
                .withCategory("Dessert")
                    .withElement("Dessert", dish2, 18.9).build();
        Menu menu3 = new Menu.Builder("Drinks")
                .withCategory("Coffee").build();

        // Creating an owner.
        Owner owner1 = new Owner.Builder("owner1@email.com", "123abcABC")
                .withPhoneNumber("0723456789")
                .withName("Mihai")
                .withSurname("Andrei").build();

        // Creating a restaurant.
        Restaurant restaurant1 = new Restaurant.Builder(owner1, address1, "Happy cheese")
                .withRestaurantType(RestaurantType.Family_Style)
                .withMenu(menu1).withMenu(menu2)
                .withPhoneNumber("0712345678").build();

        //Creating a client.
        Client client1 = new Client.Builder("client1@email.com", "aB1").withPhoneNumber("0798765432").build();

        //Creating a driver.
        Driver driver1 = new Driver.Builder("driver1@email.com", "aB1").withPhoneNumber("0767823456").build();

        System.out.println("Registering a client:");
        clientService = ClientService.getInstance();
        clientService.Register("client2@emial.com", "A17a");
        System.out.println("\n");

        // Adding an address for this client.
        clientService.addAddress(address2, AddressIdentifier.Home);

        // Adding things to cart.
        clientService.addToCart(restaurant1, menu2, "Dessert", dish2, 2);
        System.out.println("\n");

        // Attempting to add another item to cart.
        // It's not added because the dish does belong in that category.
        clientService.addToCart(restaurant1, menu1, "Alcoholic", dish3, 2);
        System.out.println("\n");

        // Attempting to add another item to cart.
        // It's not added because the category has a typo.
        clientService.addToCart(restaurant1, menu1, "Alcohoic", dish3, 2);
        System.out.println("\n");

        System.out.println("Seeing the cart after attempting to add the elements: \n");
        System.out.println(((Client) clientService.getLoggedInUser()).getCart());
        System.out.println("\n");

        // Removing the portions from cart,
        clientService.removeFromCart(restaurant1, dish2, 2 );
        System.out.println("\n");


        System.out.println("Seeing the effects removing the portions from the cart: \n");
        System.out.println(((Client) clientService.getLoggedInUser()).getCart());
        System.out.println("\n");

        // Creating a new order for this client.
        Order order1 = new Order.Builder(restaurant1, client1).withDish(dish1, 10.0).build();

        System.out.println("Seeing the new order created: ");
        System.out.println(order1);
        System.out.println("\n");

        // Changing the number of portions in the order.
        orderService.setPortionsForDish(order1, dish1, 2);

        System.out.println("Observing how the portions changed");
        System.out.println(order1);
        System.out.println("\n");

        // Adding something to cart.
        clientService.addToCart(restaurant1, menu2, "Soups", dish1, 2);
        System.out.println("\n");

        // Attempting to finish the delivery to home.
        clientService.finishOrder();
        System.out.println("\n");

        System.out.println("Observing that the order was added.");
        System.out.println(((Client) clientService.getLoggedInUser()).getOrders());
        System.out.println("\n");

        // Adding the restaurant to favourites.
        clientService.addRestaurantToFavourites(restaurant1);
        System.out.println("\n");

        // Observing that it has been added.
        System.out.println(((Client) clientService.getLoggedInUser()).getFavourites());
        System.out.println("\n");


        System.out.println("\nThe cart of the current user before heading for delivery: \n");
        System.out.println(((Client) clientService.getLoggedInUser()).getCart());

        clientService.addToCart(restaurant1, menu2, "Sides", dish3, 1);

        clientService.finishDelivery(AddressIdentifier.Home);
        System.out.println("\n");

        // The cart was already empty here:
        cartService.removeFromCart(restaurant1, dish4);
        System.out.println("\n");

        clientService.leaveAReview(5, "Lovely", restaurant1);
        System.out.println();

        // Logging out to log in as owner.
        platformService.LogOut();

        // Logging in as owner.
        ownerService.LogIn("owner1@email.com", "123abcABC");

        // Creating a new restaurant to add to the owner's list.
        Restaurant restaurant2 = new Restaurant.Builder(address3,"Coffee <3").build();

        // Adding the restaurant.
        ownerService.addRestaurant(restaurant2);

        System.out.println(((Owner)(platformService.getLoggedInUser())).getRestaurants());

        restaurantService.addMenu(menu3, restaurant2);

        menuService.addDish(restaurant2, menu3, "Coffee", dish5, 11.5);

        menuService.addCategory(restaurant2, menu3, "Summer drinks");

        dishService.editNumberQuantity(restaurant2, dish5, "coffee", 220);

        dishService.addIngredientToDish(restaurant2, dish5, "milk", 20, Quantity.ml);

        System.out.println(restaurant2.getMenus());
        System.out.println("\n");
        // Logging out to log in as a driver.
        ownerService.LogOut();

        platformService.LogIn("driver1@email.com", "aB1");

        System.out.println("Observing the delivery that we created earlier was assigned to this driver:");
        driverService.showDeliveriesToday();










//        System.out.println(driver1);
//        System.out.println(address1);
//        System.out.println(address2);
//        System.out.println(address3);
//        System.out.println(menu1);
//        System.out.println(menu2);
//        System.out.println(client1);
//        System.out.println(owner1);
    }








//    public static void pickDishAsClient(Dish dish)
//    {
//        System.out.println("How many portions?");
//        String option = scanner.next();
//        PlatformService.addToCart()
//
//
//        switch (option) {
//            case "1":
//                System.out.println("Which dish do you wish to pick? Introduce its number.\n");
//                String whichDish = scanner.next();
//                if (Integer.valueOf(whichDish).compareTo(j) < 0) {
//                    pickDishAsClient(h.get(Integer.valueOf(whichDish)));
//                }
//            case "2":
//                PlatformService.LogOut();
//                unregistered();
//            case "3":
//
//            case "4":
//                return;
//
//            default:
//                System.out.println("Something went wrong.");
//                showMenuAsClient(menu);
//        }
//    }











}
