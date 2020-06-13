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

                    //Dummy recipe
                    Recipe r1 = new Recipe("Baked potato",0);
                    Step s1 = new Step("Preheat oven to 180 degrees, poke holes in potato with a fork",0);
                    Step s2 = new Step("Place potato in oven for 1 hour",1);
                    Ingredient i1 = new Ingredient("1 Potato");
                    s1.setRecipeStepID(r1.getRecipeId()); s2.setRecipeStepID(r1.getRecipeId()); i1.setRecipeIngredientID(r1.getRecipeId());
                    INSTANCE.recipeDao().insertRecipe(r1);
                    INSTANCE.recipeDao().insertIngredient(i1);
                    INSTANCE.recipeDao().insertStep(s1);
                    INSTANCE.recipeDao().insertStep(s2);
                }
            }
        }
        return INSTANCE;
    }


}
