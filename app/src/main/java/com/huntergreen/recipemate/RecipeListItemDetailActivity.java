package com.huntergreen.recipemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import database.Recipe;
import databaseManager.DatabaseManager;

public class RecipeListItemDetailActivity extends AppCompatActivity {

    private DatabaseManager dbm;
    private Recipe recipe;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list_item_detail);
        dbm = new DatabaseManager(getApplicationContext());
        intent = getIntent();
        recipe = dbm.getRecipe(intent.getStringExtra("name"));
    }

    public void generateIngredients(){}
    /*
        Get information about recipe through intent position var
        Create text views for each ingredient
        Create text views for each step
        Include a rating bar, to update rating of recipe
     */
}
