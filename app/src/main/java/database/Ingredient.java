package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id")
    private int id;

    @ColumnInfo(name = "ingredient_identifier")
    private String identifier;

    @ColumnInfo(name = "recipe_ingredient_id")
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
