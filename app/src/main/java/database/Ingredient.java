package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int quantity;
    private double measurement;
    private String name;

    public Ingredient(int quantity, double measurement, String name){
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

    public double getMeasurement() {
        return measurement;
    }

    public String getName() {
        return name;
    }

}
