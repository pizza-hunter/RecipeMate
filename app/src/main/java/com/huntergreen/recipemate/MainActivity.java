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

    //RecipeDB db = Room.databaseBuilder(getApplicationContext(),
           // RecipeDB.class, "RecipeDatabase").build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateNewRecipeButton();
    }

    private void initiateNewRecipeButton() {
        Button newRecipeButton = findViewById(R.id.btnNewRecipe);
        final Intent intent = new Intent(this, RecipeCreateActivity.class);
        newRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(intent));
            }
        });
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup container) {
            return null;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return super.getDropDownView(position, convertView, parent);
        }
    }
}
