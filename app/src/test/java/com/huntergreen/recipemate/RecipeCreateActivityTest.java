package com.huntergreen.recipemate;

import android.content.Context;
import android.os.Build;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import database.RecipeDB;
import database.RecipeDao;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class RecipeCreateActivityTest {

    private RecipeDao recipeDao;
    private RecipeDB db;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.databaseBuilder(context, RecipeDB.class,"RecipeDB").allowMainThreadQueries().build();
        recipeDao = db.recipeDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }



    /*
        Tests
        Input tests (allow inputs)
            Recipe name
            Ingredient name
            step name
        Add Button tests (buttons cause item to be added to list)
            test corresponding input fields are empty after pressing
        Listview tests
            value entered in input appears as list item
        Save button test
            Items are added to db.
     */





}
