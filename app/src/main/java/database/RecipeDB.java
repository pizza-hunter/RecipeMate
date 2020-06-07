package database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Recipe.class, Step.class, Ingredient.class})
public abstract class RecipeDB extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    private static volatile RecipeDB INSTANCE;

    /*
        Method derived from : https://www.youtube.com/watch?v=WquAAoBFBPU&ab_channel=yoursTRULY
        Double null check is used to ensure multiple singletons are not initialised on multiple threads
     */
    public static RecipeDB getInstance(Context context){
        if(INSTANCE == null){
            synchronized (RecipeDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RecipeDB.class, "Recipe_Database").build();
                }
            }
        }
        return INSTANCE;
    }


}
