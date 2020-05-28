package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Recipe.class, Step.class, Ingredient.class})
public abstract class RecipeDB extends RoomDatabase {

    public abstract RecipeDao recipeDao();


}
