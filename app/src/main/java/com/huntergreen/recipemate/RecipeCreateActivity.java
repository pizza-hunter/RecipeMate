package com.huntergreen.recipemate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

import database.Ingredient;
import database.Recipe;
import database.Step;
import databaseManager.DatabaseManager;

public class RecipeCreateActivity extends AppCompatActivity {


    //Recipe section
    public Recipe recipe; //todo: change to private
    private EditText recipeNameEditText;

    //Ingredient section
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> ingredientStrings;
    private EditText ingredientEditText;
    private ListView ingredientListView;

    //Steps section
    private ArrayList<Step> steps;
    private ArrayList<String> stepStrings;
    private EditText stepEditText;
    private ListView stepListView;
    private int stepCounter;

    private DatabaseManager dbm;

    //TODO: remove getter methods for lists after testing
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    public ArrayList<String> getIngredientStrings() {
        return ingredientStrings;
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public ArrayList<String> getStepStrings() {
        return stepStrings;
    }
    /*
        Issues
        List items are too tall.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);
        dbm = new DatabaseManager(getApplicationContext());

        recipeNameEditText = findViewById(R.id.recipeNameEditText);

        ingredients = new ArrayList<Ingredient>();
        ingredientStrings = new ArrayList<String>();
        ingredientEditText = findViewById(R.id.ingredientNameEditText);
        ingredientListView = findViewById(R.id.ingredientListView);

        steps = new ArrayList<Step>();
        stepStrings = new ArrayList<String>();
        stepEditText = findViewById(R.id.stepEditText);
        stepListView = findViewById(R.id.stepListView);
        stepCounter = 0;

    }

    //Method for hiding keyboard from https://stackoverflow.com/questions/13593069/androidhide-keyboard-after-button-click/13593232
    private void hideKeyboard() throws Exception{
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception e){
            System.out.println("Keyboard not active");
        }
    }

    private void updateStepListView() {
        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,stepStrings);
        stepListView.setAdapter(adapter);
    }

    private void updateIngredientListView() {
        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ingredientStrings);
        ingredientListView.setAdapter(adapter);
    }

    public void addIngredientButton(View view){
        if(ingredientEditText.getText().length() > 0){
            ingredientStrings.add(ingredientEditText.getText().toString());
            updateIngredientListView();
            ingredientEditText.setText("");
            try {
                hideKeyboard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                createToast("Please enter an ingredient");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addStepButton(View view){
        if(stepEditText.getText().length() > 0){
            stepStrings.add(stepEditText.getText().toString());
            updateStepListView();
            stepEditText.setText("");
            try {
                hideKeyboard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                createToast("Please enter a step");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveButtonOnClick(View view){
        if(recipeHasIngredientsAndSteps()) {
            createRecipe();
            createIngredients();
            createSteps();
            final Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            try {
                createToast("Please enter a value in all fields");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createToast(String message) throws Exception {
        Toast toast = Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean recipeHasIngredientsAndSteps() {
        return recipeNameEditText.getText() != null &&
                ingredientStrings.size() > 0 &&
                stepStrings.size() > 0;
    }

    private void createSteps(){
        for (String stepName: stepStrings
             ) {
            steps.add(new Step(stepName,stepCounter,recipe.getRecipeId()));
            stepCounter++;
        }
        dbm.insertSteps(steps,recipe);
    }

    private void createIngredients(){
        for (String identifier: ingredientStrings
             ) {
            ingredients.add(new Ingredient(identifier,recipe.getRecipeId()));
        }
        dbm.insertIngredients(ingredients,recipe);
    }

    private void createRecipe() {
        this.recipe = new Recipe(this.recipeNameEditText.getText().toString(),0);
        dbm.insertRecipe(recipe);
    }

}
