package Classes;

import java.time.LocalDate;
import java.util.*;

public class Review {
    private User user;
    private String text;
    private LocalDate date;
    private Integer nrStars;

    public static class Builder {

        private Review review = new Review();

        public Review build() {
            review.date = LocalDate.now();
            return this.review;
        }

        public Builder(User user, Integer nrStars) {
            review.user = user;
            review.nrStars = nrStars;
        }

        public Review.Builder withText(String text) {
            review.text = text;
            return this;
        }

    }

    @Override
    public String toString() {
        return "Review{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", nrStars=" + nrStars +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNrStars() {
        return nrStars;
    }

    public void setNrStars(int nrStars) {
        this.nrStars = nrStars;
    }
}
