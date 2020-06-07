package com.huntergreen.recipemate;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import database.Ingredient;
import database.Recipe;
import database.Step;

public class RecipeCreateActivity extends AppCompatActivity {


    //Recipe section
    private Recipe recipe;
    private EditText recipeNameEditText;

    //Ingredient section
    private List<Ingredient> ingredients;
    private ArrayList<String> ingredientStrings;
    private EditText ingredientEditText;
    private ListView ingredientListView;

    //Steps section
    private List<Step> steps;
    private ArrayList<String> stepStrings;
    private EditText stepEditText;
    private ListView stepListView;
    private int stepCounter;

    /*
        Issues
        List items are too tall.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);

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

        initiateSaveButton();
        initiateAddIngredientButton();
        initiateAddStepButton();

    }

    private void initiateAddStepButton(){
        Button addStepButton = findViewById(R.id.btnAddStep);
        addStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepEditText.getText().length() > 0){
                    steps.add(new Step(stepEditText.getText().toString(), stepCounter));
                    stepStrings.add(steps.get(stepCounter).getStepString());
                    stepCounter++;
                    updateStepListView();
                    stepEditText.setText("");
                    try {
                        hideKeyboard();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                createToast("Please enter a step");
            }
        });
    }

    //Method for hiding keyboard from https://stackoverflow.com/questions/13593069/androidhide-keyboard-after-button-click/13593232
    private void hideKeyboard() throws Exception{
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            System.out.println("Keyboard not active");
        }
    }

    private void updateStepListView() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stepStrings);
        stepListView.setAdapter(adapter);
    }

    private void updateIngredientListView() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ingredientStrings);
        ingredientListView.setAdapter(adapter);
    }

    private void initiateAddIngredientButton(){
        Button addIngredientButton = findViewById(R.id.btnAddIngredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ingredientEditText.getText().length() > 0){
                    Ingredient ingredient = new Ingredient(ingredientEditText.getText().toString());
                    ingredients.add(ingredient);
                    ingredientStrings.add(ingredient.getIdentifier());
                    updateIngredientListView();
                    ingredientEditText.setText("");
                }
                createToast("Please enter an ingredient");
            }
        });
    }

    private void initiateSaveButton(){
        Button saveButton = findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recipeHasIngredientsAndSteps()) {
                    createRecipe();
                }
                else{
                    createToast("Please enter a value in all fields");
                }
            }

        });
    }

    private void createToast(String message) {
        //todo:empty method
    }

    private boolean recipeHasIngredientsAndSteps() {
        return recipeNameEditText.getText() != null &&
                ingredients.size() > 0 &&
                steps.size() > 0;
    }


    private void createRecipe() {
        //todo: change rating from hardcoded values
        this.recipe = new Recipe(this.recipeNameEditText.getText().toString(),0);
    }





}
