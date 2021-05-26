package Functionalities;

import Classes.*;
import org.javatuples.Pair;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class DishService extends PlatformService{
    private List<Dish> dishes;
    private static DishService instance;
    Audit audit;

    private DishService()
    {
        audit = Audit.getInstance("F:\\Github\\FoodStacks\\Data\\Audit.csv");
    }

    public static DishService getInstance()
    {
        if(instance==null)
        {
            instance = new DishService();
        }
        return instance;
    }
    

    public void addIngredientToDish(Restaurant restaurant, Dish dish, String ingredient, Integer number, Quantity quantity)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser))
        {
            Pair<Integer, Quantity> pair = new Pair<Integer, Quantity>(number, quantity);
            HashMap<String, Pair<Integer , Quantity>> recipe = dish.getRecipe();
            recipe.put(ingredient, pair);
            dish.setRecipe(recipe);
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

    public void removeIngredient(Restaurant restaurant, Dish dish, String ingredient)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser))
        {
            Pair<Integer, Quantity> pair = dish.getRecipe().get(ingredient);
            HashMap<String, Pair<Integer, Quantity>> ingr = dish.getRecipe();
            ingr.remove(ingredient, pair);
            dish.setRecipe(ingr);
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

    public void editQuantity(Restaurant restaurant, Dish dish, String ingredient, Quantity newQuantity)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser))
        {
            Pair<Integer, Quantity> pair = dish.getRecipe().get(ingredient);
            pair = pair.setAt1(newQuantity);
            HashMap<String, Pair<Integer, Quantity>> ingr = dish.getRecipe();
            ingr.remove(ingredient, pair);
            dish.setRecipe(ingr);
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

    public void editNumberQuantity(Restaurant restaurant, Dish dish, String ingredient, Integer  newQuantity)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser) && verifyIngredientInDish(ingredient, dish))
        {
            Pair<Integer, Quantity> pair = dish.getRecipe().get(ingredient);
            pair = pair.setAt0(newQuantity);
            HashMap<String, Pair<Integer, Quantity>> ingr = dish.getRecipe();
            ingr.remove(ingredient, pair);
            dish.setRecipe(ingr);
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

    public boolean verifyIngredientInDish(String ingredient, Dish dish)
    {
        audit.writeToFile();
        return dish.getRecipe().containsKey(ingredient);
    }

//    public static Dish dishFromInput()
//    {
//        System.out.println("Introduce dish name: ");
//        String name = scanner.next();
//        System.out.println("How many ingredients do you wish to add? ");
//        int n = parseInt(scanner.next());
//        Dish dish = new Dish.Builder(name).build();
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
//            addIngredientToDish(dish, ingredient, number, quantity);
//            n--;
//        }
//        return dish;
//    }
}
