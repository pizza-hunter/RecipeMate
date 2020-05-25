package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Utensil {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Utensil(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
