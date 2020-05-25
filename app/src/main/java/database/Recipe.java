package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Utensil> utensils;
    private ArrayList<String> steps;
    private String cookingTime;
    private String prepTime;
    private double rating;


    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<Utensil> utensils, ArrayList<String> steps, String cookingTime, String prepTime, double rating) {
        this.ingredients = ingredients;
        this.utensils = utensils;
        this.steps = steps;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.rating = rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Utensil> getUtensils() {
        return utensils;
    }

    public ArrayList<String> getSteps() {
        return steps;
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
            Array: ingredients
                Int : quantity
                String : measurement
                String : ingredient
            Array: Utensils
                String : utensil
            Array: Steps
                String : step
            String : Cooking time
            String : Prep time
            Double : Rating (5 stars)


 */