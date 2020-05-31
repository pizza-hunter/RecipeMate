package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true) private int id;
    private String identifier;
    private long recipeIngredientID;


    public Ingredient(String identifier){
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public long getRecipeIngredientID() {
        return recipeIngredientID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setRecipeIngredientID(long recipeIngredientID) {
        this.recipeIngredientID = recipeIngredientID;
    }
}
