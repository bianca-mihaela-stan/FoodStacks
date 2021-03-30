package Functionalities;

import Classes.*;
import jdk.jfr.Category;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Float.min;

public class ClientService extends PlatformService {

    private static ClientService instance;

    private ClientService()
    {

    }


    public static ClientService getInstance()
    {
        if(instance==null)
        {
            instance = new ClientService();
        }
        return instance;
    }

    public void Register(String email, String password)
    {

        if(Client.getClientsByEmail().containsKey(email))
        {
            System.out.println("This email already has an account!");
            return;
        }

        if(!validateEmail(email))
        {
            System.out.println("You need to type a valid email!");
            return;
        }


        if(!validatePassword(password)){
            System.out.println("You need to type a valid password (1 uppercase, 1 lowercase, 1 number)");
            return;
        }
        else
        {
            System.out.println("Successfully registered!");
            loggedInUser = new Client.Builder(email, password).build();
        }

    }


    public void editAddress(AddressIdentifier addressIdentifier, Address address)
    {
        if(loggedInUser instanceof Client)
        {
            HashMap<AddressIdentifier, Address> addresses = ((Client) loggedInUser).getAddresses();
            if(addresses.containsKey(addressIdentifier))
            {
                addresses.put(addressIdentifier, address);
                ((Client) loggedInUser).setAddresses(addresses);
                System.out.println("The address identifier was successfully edited!");
            }
            else
            {
                System.out.println("Address identifier does not exist!");
            }
        }
    }

    public void addAddress(Address address, AddressIdentifier addressIdentifier)
    {
        if( loggedInUser instanceof Client)
        {
            HashMap<AddressIdentifier, Address> addresses = ((Client) loggedInUser).getAddresses();
            if(addresses.containsKey(addressIdentifier))
            {
                editAddress(addressIdentifier, address);
            }
            else
            {
                addresses.put(addressIdentifier, address);
                ((Client) loggedInUser).setAddresses(addresses);
                System.out.println("Address was successfully added!");
            }
        }
    }

    public void deleteAddress(AddressIdentifier addressIdentifier)
    {
        if(loggedInUser instanceof Client)
        {
            HashMap<AddressIdentifier, Address> addresses = ((Client) loggedInUser).getAddresses();
            if(addresses.containsKey(addressIdentifier))
            {
                addresses.remove(addressIdentifier);
                ((Client) loggedInUser).setAddresses(addresses);
                System.out.println("Address was successfully removed!");
            }
            else
            {
                System.out.println("The address identifier was not found!");
            }

        }
    }


    public void addRestaurantToFavourites(Restaurant restaurant)
    {
        if(loggedInUser instanceof Client)
        {
            var favourites = ((Client) loggedInUser).getFavourites();
            if(favourites.containsKey(restaurant.getRestaurantType()) && !favourites.get(restaurant.getRestaurantType()).contains(restaurant))
            {
                var ofRestaurantType = favourites.get(restaurant.getRestaurantType());
                ofRestaurantType.add(restaurant);
                favourites.put(restaurant.getRestaurantType(), ofRestaurantType);
                ((Client) loggedInUser).setFavourites(favourites);
                System.out.println("Restaurant "+ restaurant + " was successfully added to your favourites!");
            }
            else if (favourites.containsKey(restaurant.getRestaurantType()) && favourites.get(restaurant.getRestaurantType()).contains(restaurant))
            {
                System.out.println("Restaurant is already marked as favourite!");
            }
            else
            {
                List<Restaurant> restaurants = new ArrayList<Restaurant>();
                restaurants.add(restaurant);
                favourites.put(restaurant.getRestaurantType(), restaurants);
                System.out.println("Restaurant "+ restaurant + " was successfully added to your favourites!");
            }
        }
        else
        {
            System.out.println("You are not a client!");
        }
    }

    public void removeRestaurantToFavourites(Restaurant restaurant)
    {
        if(loggedInUser instanceof Client)
        {
            var favourites = ((Client) loggedInUser).getFavourites();
            if(favourites.values().contains(restaurant))
            {
                var ofRestaurantType = favourites.get(restaurant.getRestaurantType());
                ofRestaurantType.add(restaurant);
                favourites.remove(restaurant.getRestaurantType(), ofRestaurantType);
                ((Client) loggedInUser).setFavourites(favourites);
                System.out.println("Restaurant "+ restaurant + " was successfully removed from your favourites!");
            }
            else
            {
                System.out.println("Restaurant is not marked as favourite!");
            }
        }
        else
        {
            System.out.println("You are not a client!");
        }
    }

    public void addToCart(Restaurant restaurant, Menu menu, String category, Dish dish, Integer number_of_portions)
    {
        if(loggedInUser instanceof Client && number_of_portions>0 && verifyDishInCategoryInMenu(dish, category, menu))
        {
            Cart cart = ((Client) loggedInUser).getCart();
            var dishes = cart.getDishes();
            if(dishes.containsKey(restaurant))
            {
                var list = dishes.get(restaurant);
                var price = getPriceOf(dish, restaurant, menu, category);
                if(price!= null)
                {
                    list.add(new Triplet<Dish, Integer, Double>(dish, number_of_portions, price));
                    dishes.put(restaurant, list);
                    cart.setDishes(dishes);
                    cart.setPrice(cart.getPrice()+price*number_of_portions);
                    ((Client) loggedInUser).setCart(cart);
                    System.out.println("The dish "+ dish + " was successfully added to your cart!");
                }
                else
                {
                    System.out.println("The price for this dish is null, cannot continue!");
                }
            }
            else
            {
                 var list = new ArrayList<Triplet<Dish, Integer, Double>>();
                 var price = getPriceOf(dish, restaurant, menu, category);
                 if(price!=null)
                 {
                     list.add(new Triplet<>(dish, number_of_portions, price));
                     dishes.put(restaurant, list);
                     cart.setDishes(dishes);
                     cart.setPrice(cart.getPrice()+price*number_of_portions);
                     ((Client) loggedInUser).setCart(cart);
                     System.out.println("The dish "+ dish + " was successfully added to your cart!");
                 }
            }
        }
    }

    public void removeFromCart(Restaurant restaurant, Dish dish, Integer number_of_portions)
    {
        if(loggedInUser instanceof Client && number_of_portions>0)
        {
            Cart cart = ((Client) loggedInUser).getCart();
            var dishes = cart.getDishes();
            if(dishes.containsKey(restaurant))
            {
                var list = dishes.get(restaurant);

                for(var elem: list)
                {
                    if(elem.getValue0().equals(dish))
                    {
                        if(elem.getValue1()<=number_of_portions)
                        {
                            list.remove(elem);
                            cart.setPrice(cart.getPrice()-elem.getValue1()*elem.getValue2());
                            System.out.println("The dish "+ dish + " was successfully removed from your cart!");
                            break;
                        }
                        else
                        {
                            elem = elem.setAt1(elem.getValue1()-number_of_portions);
                            cart.setPrice(cart.getPrice()-number_of_portions* elem.getValue2());
                            System.out.println("The dish "+ dish + " was successfully removed from your cart!");
                            break;
                        }
                    }
                }

                dishes.put(restaurant, list);
                cart.setDishes(dishes);
                ((Client) loggedInUser).setCart(cart);

            }
        }
    }

    public void removeFromCart(Restaurant restaurant, Dish dish)
    {
        if(loggedInUser instanceof Client)
        {
            Cart cart = ((Client) loggedInUser).getCart();
            var dishes = cart.getDishes();
            if(dishes.containsKey(restaurant))
            {
                var list = dishes.get(restaurant);

                for(var elem: list)
                {
                    if(elem.getValue0().equals(dish))
                    {

                        list.remove(elem);
                        cart.setPrice(cart.getPrice()-elem.getValue1()*elem.getValue2());
                        System.out.println("The dish "+ dish + " was successfully added to your cart!");
                        break;
                    }
                }

                dishes.put(restaurant, list);
                cart.setDishes(dishes);
                ((Client) loggedInUser).setCart(cart);

            }
        }
    }

    public Double getPriceOf(Dish dish, Restaurant restaurant, Menu menu, String category)
    {
        var elements = menu.getElements();
        if(elements.containsKey(category))
        {
            var dishes = elements.get(category);
            for(var elem : dishes)
            {
                if(elem.getValue0().equals(dish))
                {
                    return elem.getValue1();
                }
            }
        }
        return null;
    }

    public void finishOrder()
    {
        if(loggedInUser instanceof Client)
        {
            var cart = ((Client) loggedInUser).getCart();
            var orders = ((Client) loggedInUser).getOrders();
            for(var restaurant : cart.getDishes().keySet())
            {
                Order order = new Order.Builder(restaurant, (Client) loggedInUser).build();
                OrderService ord = OrderService.getInstance();
                for(var dish : cart.getDishes().get(restaurant))
                {
                    ord.addDish(order, dish.getValue0(), dish.getValue2(), dish.getValue1());
                }
                PlatformService platformService = getInstance();
            }
            ((Client) loggedInUser).setOrders(orders);
            cart = new Cart();
            ((Client) loggedInUser).setCart(cart);
            System.out.println("Your order was successfully processed!");
        }
        else
        {
            System.out.println("You are not a client!");
        }
    }

    public void finishDelivery(AddressIdentifier addressIdentifier)
    {
        if(loggedInUser instanceof Client)
        {
            var cart = ((Client) loggedInUser).getCart();
            var orders = ((Client) loggedInUser).getOrders();
            var address = ((Client) loggedInUser).getAddresses().get(addressIdentifier);
            for(var restaurant : cart.getDishes().keySet())
            {
                Delivery order = new Delivery.Builder(restaurant, (Client) loggedInUser, address).build();
                DeliveryService ord = DeliveryService.getInstance();
                for (var dish : cart.getDishes().get(restaurant))
                {
                    ord.addDish(order, dish.getValue0(), dish.getValue2(), dish.getValue1());
                }
                PlatformService platformService = getInstance();
                if (platformService.assignDelivery(order)) {
                    orders.add(order);
                    System.out.println("Your delivery was successfully processed!");
                }
                else {
                    System.out.println("Sorry, there aren't enough drivers to process this delivery!");
                }
            }
            ((Client) loggedInUser).setOrders(orders);
        }
        else
        {
            System.out.println("You are not a client!");
        }
    }

    public HashMap<AddressIdentifier, Address> getLoggedInUserAddresses()
    {
        if(loggedInUser instanceof Client)
        {
            return ((Client) loggedInUser).getAddresses();
        }
        return null;
    }

    public Address getAddressOfIdentifier(AddressIdentifier addressIdentifier)
    {
        if(loggedInUser instanceof Client)
        {
            return ((Client) loggedInUser).getAddresses().get(addressIdentifier);
        }
        return null;
    }

//    public static void registerFromConsole()
//    {
//        System.out.println("Email: ");
//        String email = scanner.next();
//        System.out.println("\nPassword: ");
//        String password = scanner.next();
//        int x = Register(email, password);
//        switch (x) {
//            case 0:
//                System.out.println("This email already has an account!");
//            case 1:
//                System.out.println("You need to type a valid email!");
//            case 2:
//                System.out.println("Password must have at least one uppercase, one lowercase and one number");
//            case 3:
//                System.out.println("Successfully registered!");
//                loggedInConsole();
//                return;
//            default:
//                System.out.println("Something went wrong, please reintroduce data.");
//        }
//        registerFromConsole();
//    }
//
//    public static void loggedInConsole() {
//        System.out.println("Options:\n");
//        System.out.println("1. Show restaurants\n");
//        System.out.println("2. Search for restaurant\n");
//        System.out.println("3. Filter restaurants\n");
//        System.out.println("4. Log out\n");
//        System.out.println("5. Account\n");
//        System.out.println("6. Orders\n");
//        System.out.println("7. Favourites\n");
//        System.out.println("8. Cart\n");
//        System.out.println("9: Close program");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                showRestaurantsConsole();
//            case "2":
//
//            case "3":
//
//            case "4":
//
//            case "5":
//
//            default:
//
//        }
//    }

//    public static void showRestaurantsConsole() {
//        ArrayList<Restaurant> restaurants = PlatformService.getClientRestaurants();
//        Integer j = 1;
//        for (Restaurant restaurant : restaurants) {
//            System.out.println(j + ": " + restaurant + "\n");
//            j++;
//        }
//        System.out.println("Options:\n");
//        System.out.println("1. Show a restaurant\n");
//        System.out.println("2. Go back\n");
//        System.out.println("3: Log out");
//        System.out.println("4: Close program");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Which restaurant do you want to show? Introduce its number.\n");
//                String whichRestaurant = scanner.next();
//                if (Integer.valueOf(whichRestaurant).compareTo(j) < 0) {
//                    showRestaurantConsole(restaurants.get(Integer.valueOf(whichRestaurant)));
//                }
//            case "2":
//
//            case "3":
//
//            case "4":
//
//            case "5":
//
//            default:
//
//        }
//    }

//    public static void showRestaurantConsole(Restaurant restaurant)
//    {
//        System.out.println(restaurant);
//        System.out.println("Options:\n");
//        System.out.println("1. Add to favourites\n");
//        System.out.println("2. Show menus\n");
//        System.out.println("3: Log out\n");
//        System.out.println("4: Go back \n");
//        System.out.println("5: Close program \n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                User user = PlatformService.getLoggedInUser();
//                int x = PlatformService.addRestaurantToFavourites(restaurant);
//                switch(x)
//                {
//                    case 0:
//                        System.out.println("You already marked this restaurant as favourite.");
//                    case 1:
//                        System.out.println("Successfully added as favourite!");
//                    case 2:
//                        System.out.println("You are not a client.");
//                }
//            case "2":
//                showMenusConsole(restaurant);
//            case "3":
//                PlatformService.LogOut();
//                unregisteredConsole();
//            case "4":
//                showRestaurantsConsole();
//            case "5":
//                return;
//            default:
//                System.out.println("Something went wrong.");
//                showRestaurantConsole(restaurant);
//        }
//    }
//
//    public static void showMenusConsole(Restaurant restaurant)
//    {
//        List<Menu> menus = PlatformService.getMenus(restaurant);
//
//        Integer j = 1;
//        for( Menu menu : menus)
//        {
//            System.out.println(j + ": " + menu + "\n");
//            j++;
//        }
//
//        System.out.println("Options:\n");
//        System.out.println("1. Pick a menu\n");
//        System.out.println("2: Log out\n");
//        System.out.println("3: Go back \n");
//        System.out.println("4: Close program \n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Which menu do you wish to show? Introduce its number.\n");
//                String whichMenu = scanner.next();
//                if (Integer.valueOf(whichMenu).compareTo(j) < 0) {
//                    showMenuConsole(menus.get(Integer.valueOf(whichMenu)));
//                }
//            case "2":
//                showMenusConsole(restaurant);
//            case "3":
//                PlatformService.LogOut();
//                PlatformService.unregisteredConsole();
//            case "4":
//                showRestaurantsConsole();
//            case "5":
//                return;
//            default:
//                System.out.println("Something went wrong.");
//                showRestaurantConsole(restaurant);
//        }
//    }
//
//    public static void showMenuConsole(Menu menu)
//    {
//        Integer j =1;
//        List<Pair<Dish, Float>> h = new ArrayList<Pair<Dish, Float>>();
//        for(String category : menu.getElements().keySet())
//        {
//            System.out.println(category + "\n");
//            for(Pair<Dish, Float> dish : menu.getElements().get(category))
//            {
//                System.out.println(j + ": " +dish.getValue0() + "\n");
//                System.out.println("---------------------------------------------------"+ dish.getValue1() );
//                h.add(dish);
//                j++;
//            }
//        }
//        System.out.println("Options:\n");
//        System.out.println("1. Pick a dish\n");
//        System.out.println("2: Log out\n");
//        System.out.println("3: Go back \n");
//        System.out.println("4: Close program \n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Which dish do you wish to pick? Introduce its number.\n");
//                String whichDish = scanner.next();
//                if (Integer.valueOf(whichDish).compareTo(j) < 0) {
//                    //pickDishAsClient(h.get(Integer.valueOf(whichDish)));
//                }
//            case "2":
//                PlatformService.LogOut();
//                PlatformService.unregisteredConsole();
//            case "3":
//
//            case "4":
//                return;
//
//            default:
//                System.out.println("Something went wrong.");
//                showMenuConsole(menu);
//        }
//
//    }


}
