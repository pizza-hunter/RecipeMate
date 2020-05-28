package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey
    private long recipeId;
    private String name;
    private String cookingTime;
    private String prepTime;
    private double rating;


    public Recipe(long recipeId,String name, String cookingTime, String prepTime, double rating) {
        this.recipeId = recipeId;
        this.name = name;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.rating = rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() { return name; }

    public long getRecipeId() {
        return recipeId;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public double getRating() {
        return rating;
    }
}


/*

file structure
    Array : recipes
        Item: recipe
            String : name
            Item: ingredients
                Int : quantity
                String : measurement
                String : ingredient
            Item: Steps
                String : step
            String : Cooking time
            String : Prep time
            Double : Rating (5 stars)


 */