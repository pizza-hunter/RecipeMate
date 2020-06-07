package com.huntergreen.recipemate;

import android.content.Context;
import android.os.Build;
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

import database.RecipeDB;
import database.RecipeDao;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class RecipeCreateActivityTest {

    private RecipeDao recipeDao;
    private RecipeDB db;

    private RecipeCreateActivity rca;

    private EditText recipeEditText;
    private EditText ingredientEditText;
    private EditText stepEditText;

    private String recipeName;
    private String ingredientNameEggs;
    private String ingredientNameMilk;
    private String step1Name;
    private String step2Name;


    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.databaseBuilder(context, RecipeDB.class,"RecipeDB").allowMainThreadQueries().build();
        recipeDao = db.recipeDao();
        rca = Robolectric.buildActivity(RecipeCreateActivity.class).create().get();

        recipeEditText = rca.findViewById(R.id.recipeNameEditText);
        ingredientEditText = rca.findViewById(R.id.ingredientNameEditText);
        stepEditText = rca.findViewById(R.id.stepEditText);

        recipeName = "Scrambled eggs";
        ingredientNameEggs = "2 large Eggs";
        ingredientNameMilk = "A dash of whole milk";
        step1Name = "Whisk eggs in small mixing bowl";
        step2Name = "Place skillet over medium heat, add eggs to skillet and stir until fluffy";

    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void InputEditTextTest(){
        recipeEditText.setText(recipeName);
        ingredientEditText.setText(ingredientNameEggs);
        stepEditText.setText(step1Name);

        assertEquals(recipeName, recipeEditText.getText().toString());
        assertEquals(ingredientNameEggs, ingredientEditText.getText().toString());
        assertEquals(step1Name,stepEditText.getText().toString());
    }

    /*
        Tests
        Input tests (allow inputs)
            Recipe name
            Ingredient name
            step name
        Add Button tests (buttons cause item to be added to list)
            test corresponding input fields are empty after pressing
        Listview tests
            value entered in input appears as list item
        Save button test
            Items are added to db.
     */





}
