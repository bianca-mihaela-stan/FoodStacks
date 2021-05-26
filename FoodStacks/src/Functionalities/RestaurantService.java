package Functionalities;

import Classes.Audit;
import Classes.Menu;
import Classes.Owner;
import Classes.Restaurant;

import java.util.List;

public class RestaurantService extends PlatformService{

    private static RestaurantService instance;
    Audit audit;

    private RestaurantService()
    {
        audit = Audit.getInstance("F:\\Github\\FoodStacks\\Data\\Audit.csv");
    }


    public static RestaurantService getInstance()
    {
        if(instance==null)
        {
            instance = new RestaurantService();
        }
        return instance;
    }
    
    public void addMenu(Menu menu, Restaurant restaurant)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner()!= null && restaurant.getOwner().equals(loggedInUser))
        {
            List<Menu> menus = restaurant.getMenus();
            if(menus.contains(menu))
            {
                System.out.println("The menu is already listed!");
            }
            else
            {
                menus.add(menu);
                restaurant.setMenus(menus);
                System.out.println("Menu successfully added!");
            }
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

    public void removeMenu(Restaurant restaurant, Menu menu)
    {
        audit.writeToFile();
        if(loggedInUser instanceof Owner && restaurant.getOwner().equals(loggedInUser))
        {
            var menus = restaurant.getMenus();
            menus.remove(menu);
            restaurant.setMenus(menus);
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

}
