package com.huntergreen.recipemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import database.Recipe;
import databaseManager.DatabaseManager;

public class RecipeListItemDetailActivity extends AppCompatActivity {

    private DatabaseManager dbm;
    private Recipe recipe;
    private Intent intent;
    private ArrayList<String> ingredientStrings;
    private ArrayList<String> stepStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list_item_detail);
        this.dbm = new DatabaseManager(getApplicationContext());
        this.intent = getIntent();
        this.recipe = dbm.getRecipe(intent.getStringExtra("name"));
        this.ingredientStrings = dbm.getRecipeIngredients(recipe);
        this.stepStrings = dbm.getRecipeSteps(recipe);
    }

    public void generateIngredients(){

    }
    /*
        Get information about recipe through intent position var
        Create text views for each ingredient
        Create text views for each step
        Include a rating bar, to update rating of recipe
     */
}
