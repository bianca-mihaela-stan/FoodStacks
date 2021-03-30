package Functionalities;

import Classes.Dish;
import Classes.Delivery;
import org.javatuples.Triplet;

public class DeliveryService {

    protected static DeliveryService instance;


    public static DeliveryService getInstance()
    {
        if(instance==null)
        {
            instance= new DeliveryService();
        }
        return instance;
    }

    public void addDish(Delivery delivery, Dish dish, Double price, Integer numberOfPortions)
    {
        var dishes = delivery.getDishesOrdered();
        dishes.add(new Triplet<Dish, Integer, Double>(dish, numberOfPortions, price));
        delivery.setFinalPrice(delivery.getFinalPrice() + price*numberOfPortions);
        delivery.setDishesOrdered(dishes);
    }
    public void removeDish(Delivery delivery, Dish dish)
    {
        var dishes = delivery.getDishesOrdered();
        for(var elem : dishes)
        {
            if(elem.getValue0().equals(dish))
            {
                dishes.remove(elem);
                delivery.setFinalPrice(delivery.getFinalPrice() - elem.getValue1()*elem.getValue2());
                delivery.setDishesOrdered(dishes);
                break;
            }
        }
    }
    public void setPortionsForDish(Delivery delivery, Dish dish, Integer number)
    {
        var dishes = delivery.getDishesOrdered();
        for(Triplet<Dish, Integer, Double> elem : dishes)
        {
            if(dish.equals(elem.getValue0()))
            {
                delivery.setFinalPrice(delivery.getFinalPrice() - (elem.getValue1()-number)*elem.getValue2());
                dishes.remove(elem);
                if(number>0)
                {
                    elem= elem.setAt1(number);
                    dishes.add(elem);
                }
                delivery.setDishesOrdered(dishes);
                return;
            }
        }
    }
}