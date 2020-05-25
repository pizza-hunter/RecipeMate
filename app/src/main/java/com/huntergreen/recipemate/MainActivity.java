package com.huntergreen.recipemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

 /*
    todo: query free space on device before accessing local app-specific data https://developer.android.com/training/data-storage/app-specific#query-free-space


    file structure
    Array : recipes
        Item: recipe
            Array: Ingredients
                Int : quantity
                String : measurement
                String : ingredient
            Array: Utensils
                String : utensil
            Array: Steps
                String : step
            String : Cooking time
            String : Prep time
            Int : Rating (5 stars)


 */


public class MainActivity extends AppCompatActivity {

    private boolean memoryStructureInitiated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!memoryStructureInitiated){
            initialiseMemory();
        }
    }

    private void initialiseMemory() {




        memoryStructureInitiated = true;
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
