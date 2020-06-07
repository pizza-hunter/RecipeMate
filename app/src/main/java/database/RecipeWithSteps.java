package database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithSteps {

    @Embedded public Recipe recipe;
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "recipe_step_id"
    )
    public List<Step> steps;
}
