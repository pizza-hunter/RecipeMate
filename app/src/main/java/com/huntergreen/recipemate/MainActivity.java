package com.huntergreen.recipemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import database.RecipeDB;

 /*
    todo: query free space on device before accessing local app-specific data https://developer.android.com/training/data-storage/app-specific#query-free-space

 */


public class MainActivity extends AppCompatActivity {

//    RecipeDB db = Room.databaseBuilder(getApplicationContext(),
//            RecipeDB.class, "RecipeDatabase").build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initiateNewRecipeButton(View view) {
        final Intent intent = new Intent(this, RecipeCreateActivity.class);
        startActivity(new Intent(intent));
    }

}
