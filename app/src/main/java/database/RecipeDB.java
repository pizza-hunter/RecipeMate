package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Recipe.class})
public abstract class RecipeDB extends RoomDatabase {

    public abstract RecipeDao recipeDao();



    /*
    Shoes = 10 per pair, 5 single
    Person = 5
    Ice cream = 4 per pair, 2 single
    final

    1 shoe = 5
    +
    1 person = 5, pair ice cream = 4, pair shoes = 10 :19
    *
    1 ice cream = 2

    (19 * 2) =  38  + 5 = 43

     */
}
