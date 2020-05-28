package database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithSteps {

    @Embedded public Recipe recipe;
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeStepID"
    )
    public List<Step> steps;
}
