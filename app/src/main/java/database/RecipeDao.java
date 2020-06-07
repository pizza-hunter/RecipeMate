package database;

import androidx.annotation.TransitionRes;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insertRecipe(Recipe recipe);

    @Insert
    void insertStep(Step step);

    @Insert
    void insertIngredient(Ingredient ingredient);

    @Delete
    void removeRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipes();

    @Query(("SELECT * FROM recipe WHERE recipe_id LIKE:searchID"))
    Recipe findRecipeByID(long searchID);

    @Query("SELECT * FROM recipe WHERE recipe_name LIKE :searchName")
    Recipe findRecipeByName(String searchName);

    @Transaction
    @Query("SELECT * FROM Recipe")
    List<RecipeWithIngredients> getRecipesWithIngredients();

    @Transaction
    @Query("SELECT * FROM Recipe")
    List<RecipeWithSteps> getRecipesWithSteps();

    @Transaction
    @Query("SELECT * FROM Ingredient " +
            "INNER JOIN Recipe ON Recipe.recipe_id = recipe_ingredient_id " +
            "WHERE recipe_id = :clickedRecipeID"
    )
    List<Ingredient> getRecipeIngredients(long clickedRecipeID);

    @Transaction
    @Query("SELECT * FROM Step " +
            "INNER JOIN Recipe ON Recipe.recipe_id = recipe_step_id " +
            "WHERE recipe_id = :clickedRecipeID"
    )
    List<Step> getRecipeSteps(long clickedRecipeID);

}
