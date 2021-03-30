package Classes;

import java.util.*;
import org.javatuples.Triplet;
import org.javatuples.Pair;

public class Dish {
    private String name;
    private HashMap<String, Pair<Integer , Quantity>> recipe= new HashMap<String, Pair<Integer , Quantity>>();
    private List<Review> reviews = new ArrayList<Review>();

    public static class Builder {

        private Dish dish = new Dish();

        public Dish build() {
            return this.dish;
        }

        public Builder(String name) {
            dish.name = name;
        }

        public Dish.Builder withRecipe(HashMap<String, Pair<Integer , Quantity>> recipe) {
            dish.recipe = recipe;
            return this;
        }


        public Dish.Builder withIngredient(String name, Integer number, Quantity quantity) {
            dish.recipe.put(name, new Pair<Integer, Quantity>(number, quantity));
            return this;
        }

        public Dish.Builder withReviews(List<Review> reviews) {
            dish.reviews = reviews;
            return this;
        }

        public Dish.Builder withReview(Review review) {
            dish.reviews.add(review);
            return this;
        }

    }
    
    
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name +
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

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return Objects.equals(name, dish.getName()) && Objects.equals(recipe, dish.getRecipe()) && Objects.equals(reviews, dish.getReviews());
    }
}
