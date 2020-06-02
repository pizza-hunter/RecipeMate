package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    private long recipeId;

    @ColumnInfo(name = "recipe_name")
    private String name;

    @ColumnInfo(name = "recipe_rating")
    private double rating;


    public Recipe(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() { return name; }

    public long getRecipeId() {
        return recipeId;
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