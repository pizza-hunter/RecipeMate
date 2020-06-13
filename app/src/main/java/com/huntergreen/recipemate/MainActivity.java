package com.huntergreen.recipemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import database.Recipe;
import database.RecipeDB;
import databaseManager.DatabaseManager;

 /*
    todo: query free space on device before accessing local app-specific data https://developer.android.com/training/data-storage/app-specific#query-free-space

 */


public class MainActivity extends AppCompatActivity {

    private ListView recipeListView;
    private ArrayList<String> recipeNames;
    private DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeNames = new ArrayList<>();
        initiateDatabaseManager();
        recipeListView = findViewById(R.id.listViewRecipes);
    }

    private void initiateDatabaseManager(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbm = new DatabaseManager(getApplicationContext());
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
        try {
            updateRecipeListView(recipeListView);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateRecipeListView(View view) throws InterruptedException {

        while (dbm == null){}
        for (Recipe recipe : dbm.getRecipes()
        ) {
            recipeNames.add(recipe.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, recipeNames);
        recipeListView.setAdapter(adapter);

    }

}
