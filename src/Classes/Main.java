package Classes;


//import Functionalities.OwnerService;
import Functionalities.PlatformService;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        unregistered();


    }

    public static void unregistered() {
        System.out.println("Options:\n");
        System.out.println("1. Register as client\n");
        System.out.println("2. Register as owner\n");
        System.out.println("3. Register as driver\n");
        System.out.println("4. Log in\n");
        System.out.println("5. Close program\n");
        System.out.println("Introduce option: ");
        String option = scanner.next();

        switch (option) {
            case "1":
                registerAsClient();
            case "2":

            case "3":

            case "4":

            case "5":

            default:
                System.out.println("Reintroduce option.\n ");
                unregistered();
        }
    }

    public static void registerAsClient() {
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("\nPassword: ");
        String password = scanner.next();
        int x = PlatformService.Register(email, password);
        switch (x) {
            case 0:
                System.out.println("This email already has an account!");
            case 1:
                System.out.println("You need to type a valid email!");
            case 2:
                System.out.println("Password must have at least one uppercase, one lowercase and one number");
            case 3:
                System.out.println("Successfully registered!");
                loggedInAsClient();
                return;
            default:
                System.out.println("Something went wrong, please reintroduce data.");
        }
        registerAsClient();
    }

    public static void loggedInAsClient() {
        System.out.println("Options:\n");
        System.out.println("1. Show restaurants\n");
        System.out.println("2. Search for restaurant\n");
        System.out.println("3. Filter restaurants\n");
        System.out.println("4. Log out\n");
        System.out.println("5. Account\n");
        System.out.println("6. Orders\n");
        System.out.println("7. Favourites\n");
        System.out.println("8. Cart\n");
        System.out.println("9: Close program");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                showRestaurantsAsClient();
            case "2":

            case "3":

            case "4":

            case "5":

            default:

        }
    }

    public static void showRestaurantsAsClient() {
        ArrayList<Restaurant> restaurants = PlatformService.getClientRestaurants();
        Integer j = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.println(j + ": " + restaurant + "\n");
            j++;
        }
        System.out.println("Options:\n");
        System.out.println("1. Show a restaurant\n");
        System.out.println("2. Go back\n");
        System.out.println("3: Log out");
        System.out.println("4: Close program");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                System.out.println("Which restaurant do you want to show? Introduce its number.\n");
                String whichRestaurant = scanner.next();
                if (Integer.valueOf(whichRestaurant).compareTo(j) < 0) {
                    showRestaurant(restaurants.get(Integer.valueOf(whichRestaurant)));
                }
            case "2":

            case "3":

            case "4":

            case "5":

            default:

        }
    }

    public static void showRestaurant(Restaurant restaurant)
    {
        System.out.println(restaurant);
        System.out.println("Options:\n");
        System.out.println("1. Add to favourites\n");
        System.out.println("2. Show menus\n");
        System.out.println("3: Log out\n");
        System.out.println("4: Go back \n");
        System.out.println("5: Close program \n");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                User user = PlatformService.getLoggedInUser();
                int x = PlatformService.addRestaurantToFavourites(restaurant);
                switch(x)
                {
                    case 0:
                        System.out.println("You already marked this restaurant as favourite.");
                    case 1:
                        System.out.println("Successfully added as favourite!");
                    case 2:
                        System.out.println("You are not a client.");
                }
            case "2":
                showMenusAsClient(restaurant);
            case "3":
                PlatformService.LogOut();
                unregistered();
            case "4":
                showRestaurantsAsClient();
            case "5":
                return;
            default:
                System.out.println("Something went wrong.");
                showRestaurant(restaurant);
        }
    }

    public static void showMenusAsClient(Restaurant restaurant)
    {
        List<Menu> menus = PlatformService.getMenus(restaurant);

        Integer j = 1;
        for( Menu menu : menus)
        {
            System.out.println(j + ": " + menu + "\n");
            j++;
        }

        System.out.println("Options:\n");
        System.out.println("1. Pick a menu\n");
        System.out.println("2: Log out\n");
        System.out.println("3: Go back \n");
        System.out.println("4: Close program \n");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                System.out.println("Which menu do you wish to show? Introduce its number.\n");
                String whichMenu = scanner.next();
                if (Integer.valueOf(whichMenu).compareTo(j) < 0) {
                    showMenuAsClient(menus.get(Integer.valueOf(whichMenu)));
                }
            case "2":
                showMenusAsClient(restaurant);
            case "3":
                PlatformService.LogOut();
                unregistered();
            case "4":
                showRestaurantsAsClient();
            case "5":
                return;
            default:
                System.out.println("Something went wrong.");
                showRestaurant(restaurant);
        }
    }


    public static void showMenuAsClient(Menu menu)
    {
        Integer j =1;
        List<Pair<Dish, Float>> h = new ArrayList<Pair<Dish, Float>>();
        for(String category : menu.elements.keySet())
        {
            System.out.println(category + "\n");
            for(Pair<Dish, Float> dish : menu.elements.get(category))
            {
                System.out.println(j + ": " +dish.getValue0() + "\n");
                System.out.println("---------------------------------------------------"+ dish.getValue1() );
                h.add(dish);
                j++;
            }
        }
        System.out.println("Options:\n");
        System.out.println("1. Pick a dish\n");
        System.out.println("2: Log out\n");
        System.out.println("3: Go back \n");
        System.out.println("4: Close program \n");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                System.out.println("Which dish do you wish to pick? Introduce its number.\n");
                String whichDish = scanner.next();
                if (Integer.valueOf(whichDish).compareTo(j) < 0) {
                    //pickDishAsClient(h.get(Integer.valueOf(whichDish)));
                }
            case "2":
                PlatformService.LogOut();
                unregistered();
            case "3":

            case "4":
                return;

            default:
                System.out.println("Something went wrong.");
                showMenuAsClient(menu);
        }

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


    public static void registerAsOwner()
    {
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("\nPassword: ");
        String password = scanner.next();
        int x = PlatformService.RegisterAsOwner(email, password);
        switch (x) {
            case 0:
                System.out.println("This email already has an account!");
            case 1:
                System.out.println("You need to type a valid email!");
            case 2:
                System.out.println("Password must have at least one uppercase, one lowercase and one number");
            case 3:
                System.out.println("Successfully registered!");
                loggedInAsOwner();
                return;
            default:
                System.out.println("Something went wrong, please reintroduce data.");
        }
        registerAsClient();
    }

    public static void loggedInAsOwner()
    {
        System.out.println("Options:\n");
        System.out.println("1. Show my restaurants\n");
        System.out.println("2. Add a restaurant\n");
        System.out.println("3. Log out\n");
        System.out.println("4. Account\n");
        System.out.println("5. Log out\n");
        System.out.println("6: Close program");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                showRestaurantsAsOwner();
            case "2":
                addRestaurant();
            case "3":

            case "4":

            case "5":

            default:

        }
    }

    public static void showRestaurantsAsOwner()
    {
        Owner owner = (Owner) PlatformService.getLoggedInUser();
        List<Restaurant> restaurants = PlatformService.getRestaurants(owner);
        Integer j = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.println(j + ": " + restaurant + "\n");
            j++;
        }
        System.out.println("Options:\n");
        System.out.println("1. Show a restaurant\n");
        System.out.println("2. Go back\n");
        System.out.println("3: Log out");
        System.out.println("4: Close program");
        System.out.println("Introduce option: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                System.out.println("Which restaurant do you want to show? Introduce its number.\n");
                String whichRestaurant = scanner.next();
                if (Integer.valueOf(whichRestaurant).compareTo(j) < 0) {
                    showRestaurant(restaurants.get(Integer.valueOf(whichRestaurant)));
                }
            case "2":

            case "3":

            case "4":

            case "5":

            default:

        }
    }

    public static void addRestaurant()
    {
        System.out.println("The name of the restaurant: ");
        String name = scanner.next();
        Address address = addressFromInput();

    }

    public static Address addressFromInput()
    {
        System.out.println("Pick one county: \n");
        County[] counties = County.values();
        for(int i=0; i<counties.length; i++)
        {
            System.out.println(i + ": " + counties[i] + "\n");
        }
        System.out.println("Pick county (insert its number): ");
        String whichCounty = scanner.next();
        County county = counties[parseInt(whichCounty)];
        System.out.println("Insert city: ");
        String city = scanner.next();
        System.out.println("Insert street: ");
        String street = scanner.next();
        System.out.println("Insert street number: ");
        String number = scanner.next();
        System.out.println("Insert block: (insert :s to skip) ");
        String block = scanner.next();
        if(block == ":s")
        {
            block = null;
        }
        System.out.println("Insert entrance: (insert :s to skip) ");
        String entrance = scanner.next();
        if(entrance == ":s")
        {
            entrance = null;
        }

        System.out.println("Insert floor: (insert :s to skip) ");
        var floor = scanner.next();
        if(floor == ":s")
        {
            floor = null;
        }
        Integer floorNumber = Integer.valueOf(floor);

        System.out.println("Insert apartment number: (insert :s to skip) ");
        String apartment= scanner.next();
        if(apartment == ":s")
        {
            apartment = null;
        }
        Integer apartmentNumber = Integer.valueOf(apartment);

        return new Address.Builder(county, city, street, number).withBlock(block).withEntrance(entrance).withFloor(floorNumber).withApartmentNumber(apartmentNumber).build();
    }


}
