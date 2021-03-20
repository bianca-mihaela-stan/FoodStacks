package Classes;

import java.util.*;
import org.javatuples.Pair;

public class Menu {
    HashMap<String, List<Pair<Dish, Float>>> menu;

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                '}';
    }

    public HashMap<String, List<Pair<Dish, Float>>> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<String, List<Pair<Dish, Float>>> menu) {
        this.menu = menu;
    }

    public List<Pair<Dish, Float>> getInCategory(String category)
    {
        return menu.get(category);
    }

    public String getCategoryOf(String dishName)
    {
        Iterator<String> it = menu.keySet().iterator();
        while(it.hasNext())
        {
            for(Pair<Dish, Float> elem : menu.get(it.next()))
            {
                if(dishName == elem.getValue0().getName())
                {
                    return it.next();
                }
            }
        }
        return null;
    }

    public void addCategory(String category)
    {
        List<Pair<Dish, Float>> list = new ArrayList<Pair<Dish, Float>>();
        menu.putIfAbsent(category, list);
    }

    public void removeCategory(String category)
    {
        menu.remove(category);
    }

    public void addDish(String category, Dish dish, Float price)
    {
        Pair<Dish, Float> pair = new Pair<>(dish, price);
        if(menu.containsKey(category)==true)
        {
            menu.get(category).add(pair);
        }
        else {
            List<Pair<Dish, Float>> list = new ArrayList<Pair<Dish, Float>>();
            list.add(pair);
            menu.put(category, list);
        }
    }

    public void removeDish(Dish dish)
    {
        menu.remove(getCategoryOf(dish.getName()), dish);
    }

    public void setPrice(String name, Float newPrice)
    {
        List<Pair<Dish, Float>> list = menu.get(getCategoryOf(name));
        for (Pair<Dish, Float> elem : list)
        {
            if(elem.getValue0().getName()==name)
            {
                elem.setAt1(newPrice);
            }
        }
    }

    public void setPrice(Dish dish, Float newPrice)
    {
        List<Pair<Dish, Float>> list = menu.get(getCategoryOf(dish.getName()));
        for (Pair<Dish, Float> elem : list)
        {
            if(elem.getValue0()==dish)
            {
                elem.setAt1(newPrice);
            }
        }
    }

    public Float getPrice(Dish dish)
    {
        List<Pair<Dish, Float>> list = menu.get(getCategoryOf(dish.getName()));
        for (Pair<Dish, Float> elem : list)
        {
            if(elem.getValue0()==dish)
            {
                return elem.getValue1();
            }
        }
        return null;
    }
}
