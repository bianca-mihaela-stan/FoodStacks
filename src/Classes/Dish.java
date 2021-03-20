package Classes;

import java.util.*;
import org.javatuples.Triplet;
import org.javatuples.Pair;

public class Dish {
    private String name;
    private HashMap<Ingredient, Pair<Integer , Quantity>> recipe;
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

    public HashMap<Ingredient, Pair<Integer, Quantity>> getRecipe() {
        return recipe;
    }

    public void setRecipe(HashMap<Ingredient, Pair<Integer, Quantity>> recipe) {
        this.recipe = recipe;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addIngredient(Ingredient ingredient, Integer nr, Quantity quantity)
    {
        recipe.putIfAbsent(ingredient, new Pair<Integer, Quantity>(nr, quantity));
    }

    public void setQuantity(Ingredient ingredient, Integer nr)
    {
        recipe.get(ingredient).setAt0(nr);
    }

    public void setQuantity(Ingredient ingredient, Quantity quantity)
    {
        recipe.get(ingredient).setAt1(quantity);
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }
}
