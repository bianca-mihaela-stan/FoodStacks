//package Functionalities;
//import Classes.*;
//import java.util.*;
//
//import java.util.Hashtable;
//import java.util.stream.Stream;
//import Classes.Owner;
//import org.javatuples.Pair;
//
//public class OwnerService extends PlatformService {
//    protected static OwnerService instance;
//
//    public static OwnerService getInstance()
//    {
//        if(instance==null)
//        {
//            instance= new OwnerService();
//        }
//        return instance;
//    }
//
//
//    public void removeRestaurant(Restaurant restaurant)
//    {
//        if(loggedInUser!=null && loggedInUser instanceof Owner)
//        {
//            List<Restaurant> restaurants = ((Owner)loggedInUser).getRestaurants();
//            if(restaurants.contains(restaurant))
//            {
//                restaurants.remove(restaurant);
//                System.out.println("Restaurant was successfully removed!");
//                System.out.println(restaurants);
//            }
//            else
//            {
//                System.out.println("You do not own this restaurant!");
//            }
//        }
//    }
//
//    public void addRestaurant(Restaurant restaurant)
//    {
//        if(loggedInUser!=null && loggedInUser instanceof Owner)
//        {
//            List<Restaurant> restaurants = ((Owner)loggedInUser).getRestaurants();
//            if(restaurants.contains(restaurant))
//            {
//                System.out.println("Restaurant is already listed!");
//            }
//            else
//            {
//                restaurants.add(restaurant);
//                System.out.println("Restaurant was successfully added!");
//                System.out.println(restaurants);
//            }
//        }
//    }
//
//    public void editRestaurant(Restaurant restaurant)
//    {
//        if(loggedInUser!=null && loggedInUser instanceof Owner)
//        {
//            boolean ok=false;
//            Restaurant item = null;
//            List<Restaurant> restaurants = ((Owner)loggedInUser).getRestaurants();
//            for(Restaurant rest : restaurants)
//            {
//                if(rest.getName()==restaurant.getName()) {
//                    ok=true;
//                    item = rest;
//                }
//            }
//            if(ok)
//            {
//                item=restaurant;
//                System.out.println("Restaurant was successfully edited!");
//                System.out.println(restaurants);
//            }
//            else
//            {
//                System.out.println("You don't own this erstaurant!");
//                System.out.println(restaurants);
//            }
//        }
//    }
//
//    public List<Restaurant> getRestaurants()
//    {
//        if(loggedInUser!=null)
//        {
//            return ((Owner) loggedInUser).getRestaurants();
//        }
//        return null;
//    }
//
//    public void addMenu(Menu menu, Restaurant restaurant)
//    {
//        if(loggedInUser!=null)
//        {
//            if(!((Owner) loggedInUser).getRestaurants().contains(restaurant))
//            {
//                System.out.println("You don't own this restaurant!");
//            }
//            else
//            {
//                List<Menu> menus = restaurant.getMenus();
//                if(menus.contains(menu))
//                {
//                    System.out.println("The menu is already listed!");
//                }
//                else
//                {
//                    menus.add(menu);
//                    restaurant.setMenus(menus);
//                    System.out.println("Menu successfully added!");
//                }
//
//            }
//        }
//        else
//        {
//            System.out.println("You're not logged in!");
//        }
//    }
//
//    public List<Menu> getMenus(Restaurant restaurant)
//    {
//        return restaurant.getMenus();
//    }
//
//    public void editMenu(Restaurant restaurant, Menu menu)
//    {
//        if(loggedInUser!=null)
//        {
//            if(!((Owner) loggedInUser).getRestaurants().contains(restaurant))
//            {
//                System.out.println("You don't own this restaurant!");
//            }
//            else
//            {
//                List<Menu> menus = restaurant.getMenus();
//                Menu menuToBeRemoved = null;
//                for(Menu m : menus)
//                {
//                    if(m.getName().equals(menu.getName()))
//                    {
//                        menuToBeRemoved=m;
//                    }
//                }
//                menus.remove(menuToBeRemoved);
//                menus.add(menu);
//                System.out.println("Menu was successfully edited!");
//            }
//        }
//        else
//        {
//            System.out.println("You're not logged in!");
//        }
//    }
//
//    public List<Pair<Dish, Float>> getInCategory(Menu menu, String category)
//    {
//        return menu.getElements().get(category);
//    }
//
//    public String getCategoryOf(Menu menu, String dishName)
//    {
//        Iterator<String> it = menu.getElements().keySet().iterator();
//        while(it.hasNext())
//        {
//            for(Pair<Dish, Float> elem : menu.getElements().get(it.next()))
//            {
//                if(dishName == elem.getValue0().getName())
//                {
//                    return it.next();
//                }
//            }
//        }
//        return null;
//    }
//
//    public void addCategory(Menu menu, String category)
//    {
//        List<Pair<Dish, Float>> list = new ArrayList<Pair<Dish, Float>>();
//        menu.getElements().putIfAbsent(category, list);
//    }
//
//    public void removeCategory(Menu menu, String category)
//    {
//        menu.getElements().remove(category);
//    }
//
//    public void addDish(Menu menu, String category, Dish dish, Float price)
//    {
//        Pair<Dish, Float> pair = new Pair<>(dish, price);
//        if(menu.getElements().containsKey(category))
//        {
//            menu.getElements().get(category).add(pair);
//        }
//        else {
//            List<Pair<Dish, Float>> list = new ArrayList<Pair<Dish, Float>>();
//            list.add(pair);
//            menu.getElements().put(category, list);
//        }
//    }
//
//    public void removeDish(Menu menu, Dish dish)
//    {
//        menu.getElements().remove(getCategoryOf(menu, dish.getName()), dish);
//    }
//
//    public void setPrice(Menu menu, String name, Float newPrice)
//    {
//        List<Pair<Dish, Float>> list = menu.getElements().get(getCategoryOf(menu, name));
//        for (Pair<Dish, Float> elem : list)
//        {
//            if(elem.getValue0().getName()==name)
//            {
//                elem.setAt1(newPrice);
//            }
//        }
//    }
//
//    public void setPrice(Menu menu, Dish dish, Float newPrice)
//    {
//        List<Pair<Dish, Float>> list = menu.getElements().get(getCategoryOf(menu, dish.getName()));
//        for (Pair<Dish, Float> elem : list)
//        {
//            if(elem.getValue0()==dish)
//            {
//                elem.setAt1(newPrice);
//            }
//        }
//    }
//
//    public Float getPrice(Menu menu, Dish dish)
//    {
//        List<Pair<Dish, Float>> list = menu.getElements().get(getCategoryOf(menu, dish.getName()));
//        for (Pair<Dish, Float> elem : list)
//        {
//            if(elem.getValue0()==dish)
//            {
//                return elem.getValue1();
//            }
//        }
//        return null;
//    }
//
//}
