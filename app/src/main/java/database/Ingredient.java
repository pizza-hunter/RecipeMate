package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true) private int id;

    private int quantity;
    private String measurement;
    private String name;
    private long recipeIngredientID;


    public Ingredient(int quantity, String measurement, String name){
        this.quantity = quantity;
        this.measurement = measurement;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getName() {
        return name;
    }

    public long getRecipeIngredientID() {
        return recipeIngredientID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipeIngredientID(long recipeIngredientID) {
        this.recipeIngredientID = recipeIngredientID;
    }
}
