package Classes;

import java.util.*;
import org.javatuples.Triplet;
import org.javatuples.Pair;

public class Dish {
    private String name;
    private HashMap<String, Pair<Integer , Quantity>> recipe;
    private List<Review> reviews;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", recipe=" + recipe +
                ", reviews=" + reviews +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Pair<Integer, Quantity>> getRecipe() {
        return recipe;
    }

    public void setRecipe(HashMap<String, Pair<Integer, Quantity>> recipe) {
        this.recipe = recipe;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addString(String String, Integer nr, Quantity quantity)
    {
        recipe.putIfAbsent(String, new Pair<Integer, Quantity>(nr, quantity));
    }

    public void setQuantity(String String, Integer nr)
    {
        recipe.get(String).setAt0(nr);
    }

    public void setQuantity(String String, Quantity quantity)
    {
        recipe.get(String).setAt1(quantity);
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }
}
