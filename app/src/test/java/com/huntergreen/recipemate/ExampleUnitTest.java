package com.huntergreen.recipemate;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import database.Ingredient;
import database.Recipe;
import database.RecipeDB;
import database.RecipeDao;
import database.Step;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(JUnit4.class)
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class ExampleUnitTest {


    private RecipeDao recipeDao;
    private RecipeDB db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.databaseBuilder(context,RecipeDB.class,"RecipeDB").allowMainThreadQueries().build();
        //db = Room.inMemoryDatabaseBuilder(context ,RecipeDB.class).build();
        recipeDao = db.recipeDao();
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void writeRecipeAndReadInList() throws Exception{
        Recipe bakedPotato = new Recipe(1,"Baked Potato", "60m","5m",5);
        Ingredient potato = new Ingredient(1,"500g","potato");
        Step s1 = new Step(1,"Wrap potato in alfoil then place in oven at 180 degrees C for 1 hour",1,1);
        Step s2 = new Step(2,"Let potato rest for 10 minutes and then enjoy!",2,1);


        recipeDao.insertRecipe(bakedPotato);
        recipeDao.insertIngredient(potato);
        recipeDao.insertStep(s1);
        recipeDao.insertStep(s2);



        List<Recipe> recipes = recipeDao.getAllRecipes();
        Recipe testRecipe = recipes.get(0);
        long testRecipeID = testRecipe.getRecipeId();

        List<Ingredient> ingredients = recipeDao.getRecipeIngredients(testRecipe.getRecipeId());
        List<Step>       steps       = recipeDao.getRecipeSteps(testRecipeID);

        assertEquals(1,recipeDao.getRecipesWithIngredients().size());
        assertEquals(2,recipeDao.getRecipeSteps(testRecipeID).size());

        assertEquals(testRecipe.getName(), bakedPotato.getName());
        assertEquals(ingredients.get(0).getName(), potato.getName());
        assertEquals(steps.get(0).getStep(),s1.getStep());

    }
}