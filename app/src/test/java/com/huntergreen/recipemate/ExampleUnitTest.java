package com.huntergreen.recipemate;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
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
    public void insertRecIngStepToDB(){
        RecipeCreateActivity rca = Robolectric.buildActivity(RecipeCreateActivity.class).create().get();

        EditText recipeEditText =  rca.findViewById(R.id.recipeNameEditText);
        EditText ingredientEditText = rca.findViewById(R.id.ingredientNameEditText);
        EditText stepEditText = rca.findViewById(R.id.stepEditText);

        Button addIbtn = rca.findViewById(R.id.btnAddIngredient);
        Button addSbtn = rca.findViewById(R.id.btnAddStep);
        Button savebtn = rca.findViewById(R.id.saveBtn);

        recipeEditText.setText("Baked potato");

        ingredientEditText.setText("1 potato");
        addIbtn.performClick();
        ingredientEditText.setText("1 tbps Butter");
        addIbtn.performClick();

        stepEditText.setText("Preheat oven to 180 Degrees C");
        addSbtn.performClick();
        stepEditText.setText("Place potato in oven for 1 hour");
        addSbtn.performClick();

        savebtn.performClick();

        assertEquals("Baked potato",recipeDao.findRecipeByName("Baked potato").getName());
    }

//    @Test
//    public void writeRecipeAndReadInList() throws Exception{
//        Recipe bakedPotato = new Recipe("Baked Potato",5);
//        Ingredient potato = new Ingredient("potato");
//        Step s1 = new Step("Wrap potato in alfoil then place in oven at 180 degrees C for 1 hour",1);
//        Step s2 = new Step("Let potato rest for 10 minutes and then enjoy!",2);
//
//        recipeDao.insertRecipe(bakedPotato);
//        recipeDao.insertIngredient(potato);
//        recipeDao.insertStep(s1);
//        recipeDao.insertStep(s2);
//
//        RecipeCreateActivity rca = new RecipeCreateActivity();
//        rca.onCreate(null);
//        rca.
//
//        List<Recipe> recipes = recipeDao.getAllRecipes();
//        Recipe testRecipe = recipes.get(0);
//        long testRecipeID = testRecipe.getRecipeId();
//
//        List<Ingredient> ingredients = recipeDao.getRecipeIngredients(testRecipe.getRecipeId());
//        List<Step>       steps       = recipeDao.getRecipeSteps(testRecipeID);
//
//        assertEquals(1,recipeDao.getRecipesWithIngredients().size());
//        assertEquals(2,recipeDao.getRecipeSteps(testRecipeID).size());
//
//        assertEquals(testRecipe.getName(), bakedPotato.getName());
//        assertEquals(ingredients.get(0).getIdentifier(), potato.getIdentifier());
//
//
//    }
}