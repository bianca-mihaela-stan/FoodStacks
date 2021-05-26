package Functionalities;

import Classes.*;
import org.javatuples.Pair;

import java.util.*;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class MenuService extends PlatformService{
    private static MenuService instance;
    private static Map<Restaurant, List<Menu>> menusByRestaurant = new Hashtable<>();
    Audit audit;
    private MenuService()
    {
        audit = Audit.getInstance("F:\\Github\\FoodStacks\\Data\\Audit.csv");
    }

    public static Map<Restaurant, List<Menu>> getMenusByRestaurant() {
        return menusByRestaurant;
    }

    public static void setMenusByRestaurant(Map<Restaurant, List<Menu>> menusByRestaurant) {
        MenuService.menusByRestaurant = menusByRestaurant;
    }

    public static MenuService getInstance()
    {
        if(instance==null)
        {
            instance = new MenuService();
        }
        return instance;
    }

    public void editDishPrice(Menu menu, Dish dish, Double price)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && menu.getRestaurant().getOwner().equals(loggedInUser))
        {
            var elements = menu.getElements();
            Consumer<Pair<Dish, Double>> method = (elem) -> { if(elem.getValue0().equals(dish))
                                                                {
                                                                    elem.setAt1(price);
                                                                }
            };
            elements.forEach(method);
        }
        else if(loggedInUser==null)
        {
            System.out.println("You're not logged in!");
        }
        else if(!(loggedInUser instanceof Owner))
        {
            System.out.println("You're not an owner!");
        }
        else
        {
            System.out.println("You don't own this restaurant!");
        }

    }


    public void addDish(Restaurant restaurant, Menu menu, Dish dish, Double price)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner()!= null && restaurant.getOwner().equals(loggedInUser))
        {
            var elements = menu.getElements();

            elements.add(new Pair<>(dish, price));
            menu.setElements(elements);

        }
        else if(loggedInUser==null)
        {
            System.out.println("You're not logged in!");
        }
        else if(!(loggedInUser instanceof Owner))
        {
            System.out.println("You're not an owner!");
        }
        else
        {
            System.out.println("You don't own this restaurant!");
        }
    }

    public void removeDish(Restaurant restaurant, Menu menu, Dish dish)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser))
        {
            var elements = menu.getElements();
            elements.removeIf(x -> x.getValue0().equals(dish));
            menu.setElements(elements);
        }
        else if(loggedInUser==null)
        {
            System.out.println("You're not logged in!");
        }
        else if(!(loggedInUser instanceof Owner))
        {
            System.out.println("You're not an owner!");
        }
        else
        {
            System.out.println("You don't own this restaurant!");
        }
    }

//    public static void addDish(Menu menu, String category, Dish dish, Float price)
//    {
//        Pair<Dish, Float> pair = new Pair<Dish, Float>(dish, price);
//        HashMap<String, List<Pair<Dish, Float>>> elements = menu.getElements();
//        List<Pair<Dish, Float>> list = elements.get(category);
//        list.add(pair);
//        elements.put(category, list);
//        menu.setElements(elements);
//    }
//
//    public static void removeDish(Menu menu, String category, Dish dish)
//    {
//        HashMap<String, List<Pair<Dish, Float>>> elements = menu.getElements();
//        List<Pair<Dish, Float>> elementsInThisCategory = menu.getElements().get(category);
//        for(Pair<Dish, Float> elem : elementsInThisCategory)
//        {
//            if(elem.getValue0().equals(dish))
//            {
//                elementsInThisCategory.remove(elem);
//                elements.put(category, elementsInThisCategory);
//                menu.setElements(elements);
//                return;
//            }
//        }
//    }
//
//    public static void changeNameOfCategory(Menu menu, String old_name, String new_name)
//    {
//        List<Pair<Dish, Float>> elementsInCategory = menu.getElements().get(old_name);
//        HashMap<String, List<Pair<Dish, Float>>> elements = menu.getElements();
//        elements.remove(old_name);
//        elements.put(new_name, elementsInCategory);
//        menu.setElements(elements);
//    }
//
//    public static void removeCategory(Menu menu, String name)
//    {
//        HashMap<String, List<Pair<Dish, Float>>> elements = menu.getElements();
//        elements.remove(name);
//        menu.setElements(elements);
//    }
//
//    public static Menu menuFromInput()
//    {
//        System.out.println("Introduce menu's name: ");
//        String name = scanner.next();
//        System.out.println("How many categories do you want to add to the menu? ");
//        int nrCategories = parseInt(scanner.next());
//        Menu menu = new Menu();
//        menu.setName(name);
//        HashMap<String, List<Pair<Dish, Float>>> elements = menu.getElements();
//        while(nrCategories>0)
//        {
//            System.out.println("Introduce the name of the category: ");
//            String categoryName = scanner.next();
//            elements.put(categoryName, null);
//            System.out.println("How many dishes do you want to add in this category?");
//            int nrDishes = parseInt(scanner.next());
//            while(nrDishes>0)
//            {
//                Dish dish = dishFromInput();
//                System.out.println("Introduce the price of the dish: ");
//                Float price = Float.parseFloat(scanner.next());
//                elements.get(categoryName).add(new Pair<Dish, Float>(dish, price));
//                nrDishes--;
//            }
//            nrCategories--;
//        }
//        menu.setElements(elements);
//        return menu;
//    }
//
//
//    public static void editDishPrice(Menu menu, String category, Dish dish, Integer price)
//    {
//        List<Pair<Dish, Float>> dishesInCategory = menu.getElements().get(category);
//        for (Pair<Dish, Float> element : dishesInCategory)
//        {
//            if(dish.equals(element.getValue0()))
//            {
//                element.setAt1(price);
//                menu.getElements().put(category, dishesInCategory);
//                break;
//            }
//        }
//    }

}
