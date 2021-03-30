package Classes;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Pair;

public class Menu {
    HashMap<String, List<Pair<Dish, Double>>> elements = new HashMap<String, List<Pair<Dish, Double>>>();
    String name;
    private long id;

    private static AtomicLong menuID = new AtomicLong(0);

    private static Long newID()
    {
        return menuID.incrementAndGet();
    }

    public Menu()
    {
        id = newID();
    }

    public static class Builder{
        private Menu menu = new Menu();

        public Builder(String name){
            menu.name =name;
        }
        public Menu.Builder withElements(HashMap<String, List<Pair<Dish, Double>>> elements){
            menu.elements=elements;
            return this;
        }
        public Menu.Builder withCategory(String category){
            List list = new ArrayList<Pair<Dish, Double>>();
            menu.elements.put(category, list);
            return this;
        }
        public Menu.Builder withElement(String category, Dish dish, Double price)
        {
            if(menu.elements.get(category)== null)
            {
                System.out.println("Category "+ category + "is not part of "+ menu + "\n");
                return this;
            }
            List<Pair<Dish, Double>> list = menu.elements.get(category);
            list.add(new Pair<Dish, Double>(dish, price));
            menu.elements.put(category, list);
            return this;
        }
        public Menu build()
        {
            return this.menu;
        }
    }


    public HashMap<String, List<Pair<Dish, Double>>> getElements() {
        return elements;
    }

    public void setElements(HashMap<String, List<Pair<Dish, Double>>> elements) {
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pair<Dish, Double>> getInCategory(String category)
    {
        return elements.get(category);
    }

    public String getCategoryOf(String dishName)
    {
        Iterator<String> it = elements.keySet().iterator();
        while(it.hasNext())
        {
            for(Pair<Dish, Double> elem : elements.get(it.next()))
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
        List<Pair<Dish, Double>> list = new ArrayList<Pair<Dish, Double>>();
        elements.putIfAbsent(category, list);
    }

    public void removeCategory(String category)
    {
        elements.remove(category);
    }

    public void addDish(String category, Dish dish, Double price)
    {
        Pair<Dish, Double> pair = new Pair<>(dish, price);
        if(elements.containsKey(category)==true)
        {
            elements.get(category).add(pair);
        }
        else {
            List<Pair<Dish, Double>> list = new ArrayList<Pair<Dish, Double>>();
            list.add(pair);
            elements.put(category, list);
        }
    }

    public void removeDish(Dish dish)
    {
        elements.remove(getCategoryOf(dish.getName()), dish);
    }

    public void setPrice(String name, Double newPrice)
    {
        List<Pair<Dish, Double>> list = elements.get(getCategoryOf(name));
        for (Pair<Dish, Double> elem : list)
        {
            if(elem.getValue0().getName()==name)
            {
                elem.setAt1(newPrice);
            }
        }
    }

    public void setPrice(Dish dish, Double newPrice)
    {
        List<Pair<Dish, Double>> list = elements.get(getCategoryOf(dish.getName()));
        for (Pair<Dish, Double> elem : list)
        {
            if(elem.getValue0()==dish)
            {
                elem.setAt1(newPrice);
            }
        }
    }

    public Double getPrice(Dish dish)
    {
        List<Pair<Dish, Double>> list = elements.get(getCategoryOf(dish.getName()));
        if(list != null)
            for (Pair<Dish, Double> elem : list)
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
        return "Menu{" +
                "elements=" + elements +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean equals(Object o)
    {
        if(this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.getName()) && Objects.equals(elements, menu.getElements());
    }
}
