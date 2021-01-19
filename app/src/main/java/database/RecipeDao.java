package database;

import androidx.annotation.TransitionRes;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insertRecipe(Recipe recipe);

    @Delete
    public void deleteRecipe(Recipe recipe);

    @Query("SELECT * FROM Recipe WHERE id = :id")
    public Recipe getRecipeById(String id);

    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipes();

}
