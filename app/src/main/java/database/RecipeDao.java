package database;

import androidx.room.Delete;
import androidx.room.Insert;

public interface RecipeDao {

    @Insert
    public void addRecipe(Recipe recipe);

    @Delete
    public void removeRecipe(Recipe recipe);


}
