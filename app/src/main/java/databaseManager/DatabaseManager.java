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
        }).start();
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
        }).start();
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
        }).start();
    }

    public List<Recipe> getRecipes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                returnList = (ArrayList<Recipe>) db.recipeDao().getAllRecipes();
            }
        }).start();
        return returnList;
    }

    private void setRecipesList(){

    }

    public Recipe getRecipe(final String name) {
        final Recipe[] recipe = new Recipe[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                recipe[0] = db.recipeDao().findRecipeByName(name);
            }
        });
        return recipe[0];
    }

    public ArrayList<String> getRecipeIngredients(final Recipe recipe) {
        final ArrayList<String> ingredients = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Ingredient ingredient: db.recipeDao().getRecipeIngredients(recipe.getRecipeId())
                     ) {
                    ingredients.add(ingredient.getIdentifier());
                }
            }
        });
        return ingredients;
    }

    public ArrayList<String> getRecipeSteps(final Recipe recipe) {
        final ArrayList<String> steps = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Step step: db.recipeDao().getRecipeSteps(recipe.getRecipeId())
                ) {
                    steps.add(step.getStepString());
                }
            }
        });
        return steps;
    }
}
