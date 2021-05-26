package Functionalities;
import Classes.*;
import org.javatuples.Pair;

import java.util.*;

import java.util.Hashtable;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class PlatformService {
    protected static PlatformService instance;

    protected static User loggedInUser= null;
    protected static Scanner scanner = new Scanner(System.in);
    private Audit audit;
    private ReadData readData;

    protected PlatformService()
    {
        audit = Audit.getInstance("F:\\Github\\FoodStacks\\Data\\Audit.csv");
        readData = ReadData.getInstance();
    }

    public static PlatformService getInstance()
    {
        if(instance==null)
        {
            instance= new PlatformService();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        PlatformService.loggedInUser = loggedInUser;
    }

    protected Boolean validateEmail(String email)
    {
        return email.matches("^(.+)@(.+)$");
    }

    protected Boolean validatePassword(String password)
    {
        return  password.matches(".*[A-Z]+.*") &&
                password.matches(".*[a-z]+.*") &&
                password.matches(".*[0-9]+.*");
    }

    public int getRandomNumber(int min, int max)
    {
        return(int) ((Math.random()*(max-min)) + min);
    }

    public String getCategoryOf(Menu menu, Dish dish)
    {
        Iterator<String> it = menu.getElements().keySet().iterator();
        while(it.hasNext())
        {
            for(Pair<Dish, Double> elem : menu.getElements().get(it.next()))
            {
                if(dish.equals(elem.getValue0()))
                {
                    return it.next();
                }
            }
        }
        return null;
    }


    public boolean assignDelivery(Delivery delivery)
    {
        var drivers = Driver.getDrivers();
        if (drivers.size() > 0) {
            int r = getRandomNumber(0, drivers.size());
            System.out.println(r);
            var deliveries = drivers.get(r).getDeliveries();
            if (deliveries.get(delivery.getDate()) != null) {
                var today = deliveries.get(delivery.getDate());
                today.add(delivery);
                deliveries.put(delivery.getDate(), today);
                drivers.get(r).setDeliveries(deliveries);
            } else {
                var today = new ArrayList<Delivery>();
                today.add(delivery);
                deliveries.put(delivery.getDate(), today);
                drivers.get(r).setDeliveries(deliveries);
            }
            return true;
        }
        return false;
    }

    public Double getPrice(Menu menu, Dish dish)
    {
        List<Pair<Dish, Double>> list = menu.getElements().get(getCategoryOf(menu, dish));
        for (Pair<Dish, Double> elem : list)
        {
            if(elem.getValue0()==dish)
            {
                return elem.getValue1();
            }
        }
        return null;
    }


    public void LogIn(String email, String password)
    {
        if(loggedInUser!=null)
        {
            System.out.println("You are already logged in!");
        }
        else {

            if(Client.getClientsByEmail().get(email)!=null && Client.getClientsByEmail().get(email).getPassword().equals(password)){
                loggedInUser=Client.getClientsByEmail().get(email);
                System.out.println("Successfully logged in!");
                return;
            }
            else if(Owner.getOwnersByEmail().get(email)!=null && Owner.getOwnersByEmail().get(email).getPassword().equals(password))
            {
                loggedInUser = Owner.getOwnersByEmail().get(email);
                System.out.println("Successfully logged in!");
                return;
            }
            else
            {
                for(var driver : Driver.getDrivers())
                {
                    if(driver.getEmail().equals(email) && driver.getPassword().equals(password))
                    {
                        loggedInUser = driver;
                        System.out.println("Successfully logged in!");
                        return;
                    }
                }
            }
            System.out.println("Email or password is incorrect!");
            //System.out.println(Owner.getOwnersByEmail().get(email));
        }

    }

    public void LogOut()
    {
        if(loggedInUser==null)
        {
            System.out.println("You're not logged in!");
        }
        else
        {
            loggedInUser=null;
            System.out.println("You have successfully logged out!");
        }

    }

    public boolean verifyDishInCategoryInMenu(Dish dish, String category, Menu menu)
    {
        var elements = menu.getElements();
        if(!elements.containsKey(category))
        {
            System.out.println("This category is not in this menu!");
            return false;
        }
        else
        {
            for(var elem : elements.get(category))
            {
                if(elem.getValue0()==dish)
                {
                    return true;
                }
            }
            System.out.println("This dish is not in this category!");
            return false;
        }
    }



    public static List<Menu> getMenus(Restaurant restaurant)
    {
        return restaurant.getMenus();
    }


    public static List<Restaurant> getRestaurants(Owner owner)
    {
        return owner.getRestaurants();
    }

    public static void setInstance(PlatformService instance) {
        PlatformService.instance = instance;
    }

    public static Map<String, Client> getClientsByEmail() {
        return Client.getClientsByEmail();
    }

    public void setClientsByEmail(Map<String, Client> clientsByEmail) {
        Client.setClientsByEmail(clientsByEmail);
    }

    public static Map<String, Owner> getOwnersByEmail() {
        return Owner.getOwnersByEmail();
    }

    public static void setOwnersByEmail(Map<String, Owner> ownersByEmail) {
        Owner.setOwnersByEmail(ownersByEmail);
    }


    public static ArrayList<Restaurant> getRestaurants() {
        return Restaurant.getRestaurants();
    }

    public static void setRestaurants(ArrayList<Restaurant> restaurants) {
        Restaurant.setRestaurants(restaurants);
    }

}
