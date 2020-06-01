package com.huntergreen.recipemate;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

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
        I & S inputs accept null values - changed comparison to length
        Edit texts dont delete values after pressing add
        List views are too small
        List items are too wide.
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
                if(stepEditText.getText() != null){
                    Step step = new Step(stepEditText.getText().toString(), stepCounter);
                    steps.add(step);
                    stepStrings.add(step.getStep());
                    stepCounter++;
                    updateStepListView();
                }
                createToast("Please enter a step");
            }
        });
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
                if(ingredientEditText.getText() != null){
                    Ingredient ingredient = new Ingredient(ingredientEditText.getText().toString());
                    ingredients.add(ingredient);
                    ingredientStrings.add(ingredient.getIdentifier());
                    updateIngredientListView();
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
        //todo: change recipeID & rating from hardcoded values
        this.recipe = new Recipe(1, this.recipeNameEditText.getText().toString(),5);
    }



}
