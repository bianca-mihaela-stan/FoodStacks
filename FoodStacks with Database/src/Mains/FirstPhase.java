package Mains;

import Classes.*;
import Functionalities.*;

public class FirstPhase {
    public static void main(String[] args) throws CloneNotSupportedException {
        PlatformService platformService = PlatformService.getInstance();
        AddressService addressService = AddressService.getInstance();
        CartService cartService = CartService.getInstance();
        ClientService clientService = ClientService.getInstance();
        DishService dishService = DishService.getInstance();
        DriverService driverService = DriverService.getInstance();
        MenuService menuService = MenuService.getInstance();
        OrderService orderService = OrderService.getInstance();
        OwnerService ownerService = OwnerService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();

        Address address1 = new Address("A", "25A");
        Address address2 = new Address("Mar", "105A");
        Address address3 = new Address("Raton", "101");

        // Creating 4 dishes.
        Dish dish1 = new Dish.Builder("soup").withIngredient("carrot", 50D, "g").withIngredient("water", 400D, "ml").build();
        Dish dish2 = new Dish.Builder("cake").withIngredient("chocolate", 100D, "g").withIngredient("cream", 100D, "g").build();
        Dish dish3 = new Dish.Builder("fries").withIngredient("carrots", 200D, "g").withIngredient("spices", 5D, "g").build();
        Dish dish4 = new Dish.Builder("Pina Colada").withIngredient("alcohol", 20D, "g").withIngredient("cream", 200D, "ml").build();
        Dish dish5 = new Dish.Builder("Cappuccino").withIngredient("coffee", 200D, "g").withIngredient("cream", 200D, "ml").build();

        Menu menu1 = new Menu.Builder("Drinks")
            .withElement(dish4, 20.0).build();
        Menu menu2 = new Menu.Builder("Food")
            .withElement(dish1, 15.5)
            .withElement(dish3, 9.9)
            .withElement(dish2, 18.9).build();
        Owner owner1 = new Owner.Builder("owner1@email.com", "123abcABC")
            .withPhoneNumber("0723456789")
            .withName("Mihai").build();

        // Creating a restaurant.
        Restaurant restaurant1 = new Restaurant.Builder(owner1, address1, "Happy cheese")
            .withRestaurantType(RestaurantType.Family).build();

        Menu menu3 = new Menu.Builder("Drinks").withRestaurant(restaurant1).build();

        Client client1 = new Client.Builder("client1@email.com", "aB1").withPhoneNumber("0798765432").build();

        System.out.println("Registering a client:");
        clientService.Register("client2@emial.com", "A17a");
        System.out.println("\n");

        clientService.editAddress(address2);

        clientService.addToCart(restaurant1, menu2, "Dessert", dish2, 2);
        clientService.addToCart(restaurant1, menu1, "Alcoholic", dish3, 2);

        System.out.println("Seeing the cart after attempting to add the elements: \n");
        System.out.println(((Client) clientService.getLoggedInUser()).getCart());
        System.out.println("\n");

        clientService.removeFromCart(restaurant1, dish2, 2 );

        System.out.println("Seeing the effects removing the portions from the cart: \n");
        System.out.println(((Client) clientService.getLoggedInUser()).getCart());
        System.out.println("\n");

        Order order1 = new Order.Builder(restaurant1, client1).withDish(dish1, 1, 10.0).build();

        System.out.println("Seeing the new order created: ");
        System.out.println(order1);
        System.out.println("\n");

        // Changing the number of portions in the order.
        orderService.setPortionsForDish(order1, dish1, 2);

        System.out.println("Observing how the portions changed");
        System.out.println(order1);
        System.out.println("\n");

        clientService.finishOrder();
        System.out.println("\n");

        System.out.println("Observing that the order was added.");
//        System.out.println(((Client) clientService.getLoggedInUser()).getOrders());
        System.out.println("\n");

        Restaurant restaurant2 = new Restaurant.Builder(address3,"Coffee <3").build();

        restaurantService.addMenu(menu3, restaurant2);

        menuService.addDish(restaurant2, menu3,  dish5, 11.5);

        dishService.addIngredientToDish(restaurant2, dish5, "milk", 20D, "ml");


    }

}
