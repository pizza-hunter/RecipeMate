package database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded public Recipe recipe;
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "recipe_ingredient_id"
    )
    public List<Ingredient> ingredients;
}
