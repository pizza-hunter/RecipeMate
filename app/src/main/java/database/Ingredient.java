package database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

import java.util.UUID;

@Entity(tableName = "Ingredient",
        foreignKeys = @ForeignKey(
        entity = Recipe.class,
        parentColumns = "id",
        childColumns = "recipe_id",
        onDelete = CASCADE))
public class Ingredient {

    @PrimaryKey
    public final String id;
    public final String text;
    public final String recipe_id;

    @Ignore
    public Ingredient(String text){
        this(text, null);
    }

    @Ignore
    public Ingredient(String text, String recipe_id){
        this(UUID.randomUUID().toString(), text, recipe_id);
    }

    public Ingredient(String id, String text, String recipe_id){
        this.id = id;
        this.text = text;
        this.recipe_id = recipe_id;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getRecipe_id() {
        return recipe_id;
    }
}
