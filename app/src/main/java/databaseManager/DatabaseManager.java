package databaseManager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import database.Ingredient;
import database.Recipe;
import database.RecipeDB;
import database.Step;

public class DatabaseManager {

    private RecipeDB db;
    private ArrayList<Recipe> returnList;

    public DatabaseManager(Context context){
        db = RecipeDB.getInstance(context);
    }

    //May run into issue of ingredients/steps trying to access recipe before it has been initalised.
    public void insertRecipe(final Recipe recipe){
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.recipeDao().insertRecipe(recipe);
            }
        });
    }

    public void insertIngredients(final List<Ingredient> ingredients, final Recipe recipe){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Ingredient ingredient: ingredients
                     ) {
                    ingredient.setRecipeIngredientID(recipe.getRecipeId());
                    db.recipeDao().insertIngredient(ingredient);
                }
            }
        });
    }

    public void insertSteps(final List<Step> steps, final Recipe recipe){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Step step: steps
                     ) {
                    step.setRecipeStepID(recipe.getRecipeId());
                    db.recipeDao().insertStep(step);
                }
            }
        });
    }

    public List<Recipe> getRecipes(){
        setRecipesList();
        return returnList;
    }

    private void setRecipesList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                returnList = (ArrayList<Recipe>) db.recipeDao().getAllRecipes();
            }
        });
    }

}
