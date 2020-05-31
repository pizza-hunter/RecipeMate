package com.huntergreen.recipemate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import database.Ingredient;
import database.Recipe;
import database.Step;

public class RecipeCreateActivity extends AppCompatActivity {

    //Recipe section
    private Recipe recipe;
    private RatingBar starRatingBar = findViewById(R.id.recipeRatingBar);
    private EditText recipeNameEditText = findViewById(R.id.recipeNameEditText);

    //Ingredient section
    private List<Ingredient> ingredients;
    private EditText ingredientEditText = findViewById(R.id.ingredientNameEditText);

    //Steps section
    private List<Step> steps;
    private EditText stepEditText = findViewById(R.id.stepEditText);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        starRatingBar.setMax(5);
        initiateSaveButton();


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

    private void showLayout(View layout) {
        layout.setVisibility(View.VISIBLE);
    }

    private void hideLayout(View layout) {
        layout.setVisibility(View.GONE);
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
        //todo: change recipeID from harccoded
        this.recipe = new Recipe(1, this.recipeNameEditText.getText().toString(), this.starRatingBar.getNumStars());
    }



}
