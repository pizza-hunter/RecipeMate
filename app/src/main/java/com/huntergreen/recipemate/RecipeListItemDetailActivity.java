package com.huntergreen.recipemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import database.Recipe;
import databaseManager.DatabaseManager;

public class RecipeListItemDetailActivity extends AppCompatActivity {

    private DatabaseManager dbm;
    private Recipe recipe;
    private Intent intent;
    private ArrayList<String> ingredientStrings;
    private ArrayList<String> stepStrings;

    private LinearLayout linearLayout;
    private int layoutCounter;
    private TextView recipeTitle;
    private TextView ingredientsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list_item_detail);

        linearLayout = findViewById(R.id.linearLayoutRecipeItemDetail);
        layoutCounter = 1;
        recipeTitle = findViewById(R.id.recipeNameTitle);
        ingredientsTextView = findViewById(R.id.detailIngredientsTextView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dbm = new DatabaseManager(getApplicationContext());
                intent = getIntent();
                try {
                    recipe = dbm.getRecipe(intent.getStringExtra("name")).get(0);
                    ingredientStrings = dbm.getRecipeIngredients(recipe.getName());
                    stepStrings = dbm.getRecipeSteps(recipe.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    recipeTitle.setText(recipe.getName());
                    generateIngredients();
                    }
                });
            }
        }).start();
    }

    public void generateIngredients(){
//        for (String ingredient: ingredientStrings
//             ) {
//            TextView textView = new TextView(getApplicationContext());
//            textView.setText(ingredient);
//            linearLayout.addView(textView,layoutCounter);
//            layoutCounter++;
//        }

       StringBuilder sb = new StringBuilder();
        for (String ingredient: ingredientStrings
             ) {
            sb.append(ingredient + "\n");
        }
        ingredientsTextView.setText(sb.toString());
    }
    /*
        Get information about recipe through intent name var, as ID's won't match positions if tuples are deleted.
        Create text views for each ingredient
        Create text views for each step
        Include a rating bar, to update rating of recipe
     */
}
