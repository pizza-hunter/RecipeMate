package database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

import java.util.UUID;

@Entity(tableName = "Step",
        foreignKeys = @ForeignKey
        (entity = Recipe.class,
        parentColumns = "id",
        childColumns = "recipe_id",
        onDelete = CASCADE))
public class Step {

    @PrimaryKey
    public final String id;
    public final String text;
    public final String step_number;
    public final String recipe_id;

    @Ignore
    public Step(String text, String step_number){
        this(text,step_number,null);
    }

    @Ignore
    public Step(String text, String step_number, String recipe_id) {
        this(UUID.randomUUID().toString(),text,step_number,recipe_id);
    }

    public Step(String id, String text, String step_number, String recipe_id) {
        this.id = id;
        this.text = text;
        this.step_number = step_number;
        this.recipe_id = recipe_id;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getStep_number() {
        return step_number;
    }

    public String getRecipe_id() {
        return recipe_id;
    }
}
