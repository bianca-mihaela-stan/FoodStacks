package Classes;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Pair;

public class Menu {
    HashMap<String, List<Pair<Dish, Float>>> elements = new HashMap<String, List<Pair<Dish, Float>>>();
    String name;
    private long id;

    private static AtomicLong menuID = new AtomicLong(0);

    private static Long newID()
    {
        return menuID.incrementAndGet();
    }

    Menu()
    {
        id = newID();
    }


    public HashMap<String, List<Pair<Dish, Float>>> getElements() {
        return elements;
    }

    public void setElements(HashMap<String, List<Pair<Dish, Float>>> elements) {
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pair<Dish, Float>> getInCategory(String category)
    {
        return elements.get(category);
    }

    public String getCategoryOf(String dishName)
    {
        Iterator<String> it = elements.keySet().iterator();
        while(it.hasNext())
        {
            for(Pair<Dish, Float> elem : elements.get(it.next()))
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
        elements.putIfAbsent(category, list);
    }

    public void removeCategory(String category)
    {
        elements.remove(category);
    }

    public void addDish(String category, Dish dish, Float price)
    {
        Pair<Dish, Float> pair = new Pair<>(dish, price);
        if(elements.containsKey(category)==true)
        {
            elements.get(category).add(pair);
        }
        else {
            List<Pair<Dish, Float>> list = new ArrayList<Pair<Dish, Float>>();
            list.add(pair);
            elements.put(category, list);
        }
    }

    public void removeDish(Dish dish)
    {
        elements.remove(getCategoryOf(dish.getName()), dish);
    }

    public void setPrice(String name, Float newPrice)
    {
        List<Pair<Dish, Float>> list = elements.get(getCategoryOf(name));
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
        List<Pair<Dish, Float>> list = elements.get(getCategoryOf(dish.getName()));
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
        List<Pair<Dish, Float>> list = elements.get(getCategoryOf(dish.getName()));
        for (Pair<Dish, Float> elem : list)
        {
            if(elem.getValue0()==dish)
            {
                return elem.getValue1();
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Elements{" +
                "elements=" + elements +
                ", name='" + name + '\'' +
                '}';
    }
}
