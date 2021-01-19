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

    //todo: create method that keeps track of an incremented counter for primary keys, Recipe, Ing, Step


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
                    db.ingredientDao().insertIngredient(ingredient);
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
                    db.stepDao().insertStep(step);
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



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public ArrayList<Recipe> getRecipe(final String name) throws InterruptedException {
        final ArrayList<Recipe> recipes;

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                returnList = (ArrayList<Recipe>) db.recipeDao().findRecipeByName(name);
            }
        });
        t1.start();
        t1.join();
        return returnList;
    }
    //todo: steps and ingredients foreign key for recipe id not being set
    public ArrayList<String> getRecipeIngredients(final String recipeName) throws InterruptedException {
        final ArrayList<String> ingredients = new ArrayList<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Ingredient ingredient: db.ingredientDao().getRecipeIngredients(db.recipeDao().findRecipeByName(recipeName).get(0).getRecipeId())
                     ) {
                    ingredients.add(ingredient.getIdentifier());
                }
            }
        });
        t1.start();
        t1.join();
        return ingredients;
    }

    public ArrayList<String> getRecipeSteps(final String recipeName) throws InterruptedException {
        final ArrayList<String> steps = new ArrayList<>();
       Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Step step: db.stepDao().getRecipeSteps(db.recipeDao().findRecipeByName(recipeName).get(0).getRecipeId())
                ) {
                    steps.add(step.getStepString());
                }
            }
        });
       t1.start();
       t1.join();
       return steps;
    }
}
