package com.huntergreen.recipemate;

import android.app.LauncherActivity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

    private Button btnIngredient;
    private Button btnStep;

    private ListView listViewIngredients;
    private ListView listViewSteps;

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

        btnIngredient = rca.findViewById(R.id.btnAddIngredient);
        btnStep = rca.findViewById(R.id.btnAddStep);

        listViewIngredients = rca.findViewById(R.id.ingredientListView);
        listViewSteps = rca.findViewById(R.id.stepListView);
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

    @Test
    public void addButtonsEmptyEditTextTest(){
        ingredientEditText.setText(ingredientNameEggs);
        stepEditText.setText(step1Name);

        //Test if button click empties editTexts
        btnIngredient.performClick();
        assertEquals("",ingredientEditText.getText().toString());
        btnStep.performClick();
        assertEquals("",stepEditText.getText().toString());
    }

    @Test
    public void addButtonsUpdateListsTest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ingredientEditText.setText(ingredientNameEggs);
                stepEditText.setText(step1Name);
                try {
                    wait(1000);
                    btnIngredient.performClick();
                    btnStep.performClick();
                    assertEquals(ingredientNameEggs,rca.getIngredients().get(0).getIdentifier());
                    assertEquals(step1Name,rca.getSteps().get(0).getStepString());
                    assertEquals(ingredientNameEggs,rca.getIngredientStrings().get(0));
                    assertEquals(step1Name,rca.getStepStrings().get(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Test
    public void listItemAppearsInListViewTest(){
        /*
            Add items to edit texts
            press add
            get items from list view
            compare list view items
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                ingredientEditText.setText(ingredientNameEggs);
                stepEditText.setText(step1Name);
                try {
                    wait(1000);
                    btnIngredient.performClick();
                    btnStep.performClick();
                    wait(1000);
                    Object itemIngredient = listViewIngredients.getItemAtPosition(0);
                    Object itemStep = listViewSteps.getItemAtPosition(0);
                    assertEquals(ingredientNameEggs,itemIngredient.toString());
                    assertEquals(step1Name, itemStep.toString());
                } catch (InterruptedException e) { e.printStackTrace(); }

                //Input of second ingredient and step

                ingredientEditText.setText(ingredientNameMilk);
                stepEditText.setText(step2Name);
                try {
                    wait(1000);
                    btnIngredient.performClick();
                    btnStep.performClick();
                    wait(1000);
                    Object itemIngredient = listViewIngredients.getItemAtPosition(1);
                    Object itemStep = listViewSteps.getItemAtPosition(1);
                    assertEquals(ingredientNameMilk,itemIngredient.toString());
                    assertEquals(step2Name, itemStep.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
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
