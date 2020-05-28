package database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded public Recipe recipe;
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeIngredientID"
    )
    public List<Ingredient> ingredients;
}
