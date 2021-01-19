package com.huntergreen.recipemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import database.Recipe;
import database.RecipeDB;
import databaseManager.DatabaseManager;


 /*
    todo: query free space on device before accessing local app-specific data https://developer.android.com/training/data-storage/app-specific#query-free-space
 */


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView recipeListView;
    private ArrayList<String> recipeNames;
    //Test code below
    private DatabaseManager dbm;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeNames = new ArrayList<>();
        recipeListView = findViewById(R.id.listViewRecipes);

        //todo: move this code to new method
        recipeListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), RecipeListItemDetailActivity.class);
                String recipeNameText = recipeListView.getItemAtPosition(position).toString(); //could have incorrect string value
                intent.putExtra("name", recipeNameText);
                intent.putExtra("position", position); //Use position to determine which recipe
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    public void initiateNewRecipeButton(View view) {
        final Intent intent = new Intent(this, RecipeCreateActivity.class);
        startActivity(new Intent(intent));
    }

    @Override
    public void onResume(){
        super.onResume();
        updateRecipeListView(recipeListView);
    }

    public void updateRecipeListView(View view) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                recipeNames.clear();
                dbm = new DatabaseManager(getApplicationContext());
                recipeList = dbm.getRecipes();
                for (Recipe recipe : recipeList
                ) {
                    recipeNames.add(recipe.getName());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter adapter = new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                recipeNames);
                        recipeListView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }
}
