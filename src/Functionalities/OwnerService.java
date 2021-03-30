package Functionalities;
import Classes.*;
import java.util.*;

import java.util.Hashtable;
import java.util.stream.Stream;
import Classes.Owner;
import javafx.print.PageOrientation;
import jdk.jfr.Category;
import org.javatuples.Pair;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class OwnerService extends PlatformService {
    protected static OwnerService instance;

    public static OwnerService getInstance()
    {
        if(instance==null)
        {
            instance= new OwnerService();
        }
        return instance;
    }

    public void RegisterAsOwner(String email, String password)
    {
        PlatformService platformService=getInstance();

        if(Owner.getOwnersByEmail().containsKey(email))
        {
            System.out.println("This email already has an account!");
            return;
        }

        if(!platformService.validateEmail(email))
        {
            System.out.println("You need to type a valid email!");
            return;
        }

        if(!validatePassword(password)){
            System.out.println("You need to type a valid password (1 uppercase, 1 lowercase, 1 number)");
            return;
        }

        System.out.println("Successfully logged in!");
        loggedInUser = new Owner.Builder(email, password).build();
    }


    public void removeRestaurant(Restaurant restaurant)
    {
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser))
        {
            var restaurants = ((Owner) loggedInUser).getRestaurants();
            restaurants.remove(restaurant);
            ((Owner) loggedInUser).setRestaurants(restaurants);
            System.out.println("Restaurant was successfully removed!");
        }
    }

    public void addRestaurant(Restaurant restaurant)
    {
        if(loggedInUser instanceof Owner && restaurant.getOwner()!=null && restaurant.equals(loggedInUser))
        {
            var restaurants = ((Owner) loggedInUser).getRestaurants();
            restaurants.add(restaurant);
            ((Owner) loggedInUser).setRestaurants(restaurants);
            System.out.println("Restaurant was successfully added!");
        }
        else if(loggedInUser instanceof Owner && restaurant.getOwner()==null)
        {
            var restaurants = ((Owner) loggedInUser).getRestaurants();
            restaurants.add(restaurant);
            ((Owner) loggedInUser).setRestaurants(restaurants);
            restaurant.setOwner((Owner) loggedInUser);
            System.out.println("Restaurant was successfully added!");
        }
    }

    public List<Pair<Dish, Double>> getInCategory(Menu menu, String category)
    {
        return menu.getElements().get(category);
    }


//    public static void registerConsole()
//    {
//        System.out.println("Email: ");
//        String email = scanner.next();
//        System.out.println("\nPassword: ");
//        String password = scanner.next();
//        int x = RegisterAsOwner(email, password);
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
//    }
//
//    public static void loggedInConsole()
//    {
//        System.out.println("Options:\n");
//        System.out.println("1. Show my restaurants\n");
//        System.out.println("2. Add a restaurant\n");
//        System.out.println("3. Log out\n");
//        System.out.println("4. Account\n");
//        System.out.println("5: Close program");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                showRestaurantsConsole();
//            case "2":
//                addRestaurantConsole();
//            case "3":
//                PlatformService.LogOut();
//            case "4":
//
//            case "5":
//                return;
//            default:
//
//        }
//    }
//
//
//    public static void showRestaurantsConsole()
//    {
//        Owner owner = (Owner) PlatformService.getLoggedInUser();
//        List<Restaurant> restaurants = PlatformService.getRestaurants(owner);
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
//                    showRestaurantConsole(restaurants.get(Integer.parseInt(whichRestaurant)));
//                }
//            case "2":
//                loggedInConsole();
//            case "3":
//                PlatformService.LogOut();
//            case "4":
//                return;
//            default:
//                System.out.println("Something went wrong, reloading...");
//                showRestaurantsConsole();
//        }
//    }
//
//    public static void showRestaurantConsole(Restaurant restaurant)
//    {
//        System.out.println(restaurant);
//        Owner owner = (Owner) PlatformService.getLoggedInUser();
//        System.out.println("Options:\n");
//        System.out.println("1. Show menus\n");
//        System.out.println("2. Add menu\n");
//        System.out.println("3. Edit restaurant address");
//        System.out.println("5: Edit restaurant name\n");
//        System.out.println("3. Go back\n");
//        System.out.println("4: Log out");
//        System.out.println("5: Close program");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                showMenusConsole(restaurant);
//            case "2":
//                addMenuConsole(restaurant);
//            case "3":
//                showRestaurantsConsole();
//            case "4":
//                PlatformService.LogOut();
//                PlatformService.unregisteredConsole();
//            case "5":
//                return;
//            default:
//                System.out.println("Soemthing went wrong! Reloading...");
//                showRestaurantConsole(restaurant);
//        }
//    }
//
//    public static void addMenuConsole(Restaurant restaurant)
//    {
//        Menu menu = menuFromInput();
//        RestaurantService.addMenu(restaurant, menu);
//    }
//
//    public static void showMenusConsole(Restaurant restaurant)
//    {
//        List<Menu> menus = restaurant.getMenus();
//        int j=1;
//        for(Menu menu : menus)
//        {
//            System.out.println(j + ": " + menu + "\n");
//        }
//
//        System.out.println("Options:\n");
//        System.out.println("1. Show a menu\n");
//        System.out.println("2. Go back\n");
//        System.out.println("3: Log out");
//        System.out.println("4: Close program");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Which menu do you want to show? Introduce its number: ");
//                int whichMenu = parseInt(scanner.next());
//                showMenuConsole(restaurant, menus.get(whichMenu-1));
//            case "2":
//                showRestaurantConsole(restaurant);
//            case "3":
//                PlatformService.LogOut();
//            case "4":
//                return;
//            default:
//                System.out.println("Soemthing went wrong! Reloading...");
//                showMenusConsole(restaurant);
//        }
//        showMenusConsole(restaurant);
//    }
//
//    public static void showMenuConsole(Restaurant restaurant, Menu menu)
//    {
//        System.out.println(menu);
//
//        System.out.println("Options:\n");
//        System.out.println("1. Show dishes\n");
//        System.out.println("2. Change menu name\n");
//        System.out.println("3: Go back\n");
//        System.out.println("4: Log out\n");
//        System.out.println("5: Close program\n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                showDishesConsole(restaurant, menu);
//            case "2":
//                changeMenuNameConsole(menu);
//            case "3":
//                showMenusConsole(restaurant);
//            case "4":
//                PlatformService.LogOut();
//            case "5":
//                return;
//            default:
//                System.out.println("Something went wrong! Reloading...");
//                showMenuConsole(restaurant,menu);
//        }
//        showMenuConsole(restaurant, menu);
//    }
//
//
//    public static void changeMenuNameConsole(Menu menu)
//    {
//        System.out.println("Introduce new name: ");
//        String name = scanner.next();
//        menu.setName(name);
//    }
//
//    public static void showDishesConsole(Restaurant restaurant, Menu menu)
//    {
//        Integer j = 1;
//        Integer i = 1;
//        List<Pair<Dish, Double>> h = new ArrayList<Pair<Dish, Double>>();
//        List<String> categories = new ArrayList<String>();
//        for(String category : menu.getElements().keySet())
//        {
//            System.out.println(i + ": " + category + "\n");
//            categories.add(category);
//            i++;
//            for(Pair<Dish, Double> dish : menu.getElements().get(category))
//            {
//                System.out.println(j + ": " +dish.getValue0() + "\n");
//                System.out.println("---------------------------------------------------"+ dish.getValue1() );
//                h.add(dish);
//                j++;
//            }
//        }
//        System.out.println("Options:\n");
//        System.out.println("1. Show category\n");
//        System.out.println("2. Edit category name\n");
//        System.out.println("3: Remove category\n");
//        System.out.println("4: Add category\n");
//        System.out.println("5: Log out\n");
//        System.out.println("6: Go back\n");
//        System.out.println("7: Close program\n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Which category do you want to show? Introduce its number: ");
//                int whichCategory = parseInt(scanner.next());
//                showCategoryConsole(menu, categories.get(whichCategory-1));
//            case "2":
//                System.out.println("Which category do you want to edit? Introduce its number: ");
//                whichCategory = parseInt(scanner.next());
//                editCategoryNameConsole(menu, categories.get(whichCategory-1));
//            case "3":
//                System.out.println("Which category do you want to remove? Introduce its number: ");
//                whichCategory = parseInt(scanner.next());
//                removeCategoryConsole(menu, categories.get(whichCategory-1));
//            case "4":
//                addCategoryConsole(menu);
//            case "5":
//                PlatformService.LogOut();
//            case "6":
//                showMenuConsole(restaurant, menu);
//            case "7":
//                return;
//            default:
//                System.out.println("Soemthing went wrong! Reloading...");
//                showDishesConsole(restaurant, menu);
//        }
//        showDishesConsole(restaurant, menu);
//    }
//
//    public static void editCategoryNameConsole(Menu menu, String category)
//    {
//        System.out.println("What do you want to change the name into: ");
//        String name = scanner.next();
//        MenuService.changeNameOfCategory(menu, category, name);
//    }
//
//    public static void removeCategoryConsole(Menu menu, String category)
//    {
//        MenuService.removeCategory(menu, category);
//    }
//
//    public static void addCategoryConsole(Menu menu)
//    {
//        HashMap<String, List<Pair<Dish, Double>>> elements = menu.getElements();
//        System.out.println("Introduce the name of the category: ");
//        String categoryName = scanner.next();
//        elements.put(categoryName, null);
//        System.out.println("How many dishes do you want to add in this category?");
//        int nrDishes = parseInt(scanner.next());
//        while(nrDishes>0)
//        {
//            Dish dish = dishFromInput();
//            System.out.println("Introduce the price of the dish: ");
//            Double price = Double.parseDouble(scanner.next());
//            elements.get(categoryName).add(new Pair<Dish, Double>(dish, price));
//            nrDishes--;
//        }
//        menu.setElements(elements);
//    }
//
//
//    public static void showCategoryConsole(Menu menu, String category)
//    {
//        System.out.println(category + "\n");
//        List<Pair<Dish, Double>> dishes = menu.getElements().get(category);
//        int j=1;
//        for(Pair<Dish, Double> dish : dishes)
//        {
//            System.out.println(j + ": " +dish.getValue0() + "\n");
//            System.out.println("---------------------------------------------------"+ dish.getValue1() );
//            j++;
//        }
//        System.out.println("Options:\n");
//        System.out.println("1. Edit dish\n");
//        System.out.println("2. Remove dish\n");
//        System.out.println("3: Add dish\n");
//        System.out.println("4: Log out\n");
//        System.out.println("5: Go back\n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Which dish do you want to edit? Introduce its number: ");
//                int whichDish = parseInt(scanner.next());
//                editDishConsole(menu, category, dishes.get(whichDish-1));
//            case "2":
//                System.out.println("Which dish do you want to remove? Introduce its number: ");
//                whichDish = parseInt(scanner.next());
//                removeDishConsole(menu, category);
//            case "3":
//                addDishConsole(menu, category);
//            case "4":
//                PlatformService.LogOut();
//            case "5":
//               return;
//            default:
//                System.out.println("Soemthing went wrong! Reloading...");
//                showCategoryConsole(menu, category);
//        }
//
//        showCategoryConsole(menu, category);
//    }
//
//    public static void addDishConsole(Menu menu, String category)
//    {
//        Dish dish = dishFromInput();
//        System.out.println("Introduce the price of the dish: ");
//        Double price = Double.parseDouble(scanner.next());
//        MenuService.addDish(menu, category, dish, price);
//    }
//
//    public static void editDishConsole(Menu menu, String category, Pair<Dish, Double> dish)
//    {
//        System.out.println("Options:\n");
//        System.out.println("1. Change the name of the dish\n");
//        System.out.println("2. Change the price of the dish\n");
//        System.out.println("3: Remove ingredients from dish dish\n");
//        System.out.println("4: Add ingredients to dish");
//        System.out.println("5: Log out\n");
//        System.out.println("6: Go back\n");
//        System.out.println("Introduce option: ");
//
//        String option = scanner.next();
//
//        switch (option) {
//            case "1":
//                System.out.println("Introduce new name: ");
//                String newName = scanner.next();
//                editDishName(dish.getValue0(), newName);
//            case "2":
//                System.out.println("Introduce new price: ");
//                Integer price = valueOf(scanner.next());
//                editDishPrice(menu, category, dish.getValue0(),  price);
//            case "3":
//                removeIngredientsFromDishConsole(dish.getValue0());
//            case "4":
//                addIngredientsToDishConsole(dish.getValue0());
//            case "5":
//                PlatformService.LogOut();
//                PlatformService.unregisteredConsole();
//            case "6":
//                return;
//            default:
//                System.out.println("Soemthing went wrong! Reloading...");
//                editDishConsole(menu, category, dish);
//        }
//    }
//
//    public static void removeDishConsole(Menu menu, String category)
//    {
//        HashMap<String, List<Pair<Dish, Double>>> elements = menu.getElements();
//        List<Pair<Dish, Double>> elementsInThisCategory = elements.get(category);
//        int j=1;
//        for(Pair<Dish, Double> element : elementsInThisCategory)
//        {
//            System.out.println(j+ ": " + element.getValue1() + "\n");
//        }
//        System.out.println("Which dish do you want to remove? Introduce its number. ");
//        int whichDish = parseInt(scanner.next());
//        MenuService.removeDish(menu, category, elementsInThisCategory.get(whichDish-1).getValue0());
//    }
//
//    public static void editDishName(Dish dish, String name)
//    {
//        dish.setName(name);
//    }


//    public void addIngredientsToDishConsole(Dish dish)
//    {
//        System.out.println("How many ingredients does this dish have?");
//        Integer n = valueOf(scanner.next());
//        while(n>0)
//        {
//            System.out.println("Introduce name of ingredient: ");
//            String ingredient = scanner.next();
//            System.out.println("Measurement of quantity for the ingredient: (introduce its number from the list) ");
//            int j=1;
//            for (Quantity elem : Quantity.values())
//                System.out.println(j + ": " + elem + "\n" );
//            int whichQuantity = parseInt(scanner.next());
//            Quantity quantity = Quantity.values()[whichQuantity];
//            System.out.println("Introduce quantity: ");
//            Integer number = valueOf(scanner.next());
//            DishService dishService = DishService.getInstance();
//            dishService.addIngredientToDish(dish, ingredient, number, quantity);
//            n--;
//        }
//    }
//
//    public void removeIngredientsFromDishConsole(Dish dish)
//    {
//        for(String ingredient : dish.getRecipe().keySet())
//        {
//            System.out.println(ingredient + "\n");
//        }
//        System.out.println("How many ingredients do you want to remove?");
//        int n = parseInt(scanner.next());
//        while(n>0)
//        {
//            System.out.println("Which ingredient do you want to remove? Introduce its name: ");
//            String x = (scanner.next());
//            DishService dishService = DishService.getInstance();
//            dishService.removeIngredient(dish, x);
//        }
//    }




//    public static void addRestaurantConsole()
//    {
//        System.out.println("The name of the restaurant: ");
//        String name = scanner.next();
//        Address address = addressFromInput();
//        addRestaurant(new Restaurant.Builder((Owner) loggedInUser, address, name).build());
//    }




//    public List<Restaurant> getRestaurants()
//    {
//        if(loggedInUser!=null)
//        {
//            return ((Owner) loggedInUser).getRestaurants();
//        }
//        return null;
//    }



//    public List<Menu> getMenus(Restaurant restaurant)
//    {
//        return restaurant.getMenus();
//    }



}
