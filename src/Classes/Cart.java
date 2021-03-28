package Classes;

import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.List;

public class Cart {
    protected HashMap<Restaurant, List<Triplet<Dish, Integer, Float>>> dishes;
    protected Float price;

}
