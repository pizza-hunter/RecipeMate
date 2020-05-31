package com.huntergreen.recipemate;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import database.Ingredient;
import database.Recipe;

public class RecipeCreateActivity extends AppCompatActivity {

    //Recipe section
    private Recipe recipe;
    private ConstraintLayout recipeConstraintLayout = findViewById(R.id.recipeFormConstraintLayout);
    private RatingBar starRatingBar = findViewById(R.id.recipeRatingBar);
    private EditText recipeNameEditText = findViewById(R.id.recipeNameEditText);
    private EditText recipeCookingTimeEditText = findViewById(R.id.recipeCookingTimeEditText);
    private EditText recipePrepTimeEditText = findViewById(R.id.recipePrepTimeEditText);

    //Ingredient section
    private List<Ingredient> ingredients;
    private ConstraintLayout ingredientConstraintLayout = findViewById(R.id.ingredientFormConstraintLayout);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        starRatingBar.setMax(5);
        
        Button saveButton = findViewById(R.id.saveBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recipeEditTextsNotNull()) {
                    createRecipe();
                    hideLayout(recipeConstraintLayout);
                    showLayout(ingredientConstraintLayout);
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

    private boolean recipeEditTextsNotNull() {
        return recipeNameEditText.getText() != null &&
                recipeCookingTimeEditText.getText() != null &&
                recipePrepTimeEditText.getText() != null;
    }


    private void createRecipe() {
        //todo: change recipeID from harccoded
        this.recipe = new Recipe(1, this.recipeNameEditText.getText().toString(), this.recipeCookingTimeEditText.getText().toString(),
                this.recipePrepTimeEditText.getText().toString(), this.starRatingBar.getNumStars());
    }



}
