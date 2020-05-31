package databaseManager;

import androidx.room.Dao;
import androidx.room.RoomDatabase;

import java.util.List;

import database.Ingredient;
import database.Recipe;
import database.RecipeDao;
import database.Step;

public class DatabaseManager {

    private RoomDatabase db;
    private RecipeDao dao;

    public DatabaseManager(RoomDatabase db, RecipeDao dao) {
        this.db = db;
        this.dao = dao;
    }

    public Recipe getRecipe(String name){
        return dao.findRecipeByName(name);
    }

    public List<Ingredient> getRecipeIngredients(Recipe recipe){
        return dao.getRecipeIngredients(recipe.getRecipeId());
    }

    public List<Step> getRecipeSteps(Recipe recipe){
        return dao.getRecipeSteps(recipe.getRecipeId());
    }

    public List<Recipe> getRecipes(){
        return dao.getAllRecipes();
    }

    public void insertRecipeIngredientsSteps(Recipe recipe, List<Ingredient> ingredients, List<Step> steps){
        dao.insertRecipe(recipe);
        insertIngredients(ingredients);
        insertSteps(steps);
    }

    private void insertSteps(List<Step> steps){
        for (Step step: steps
             ) {
            dao.insertStep(step);
        }
    }

    private void insertIngredients(List<Ingredient> ingredients){
        for (Ingredient ingredient: ingredients
        ) {
            dao.insertIngredient(ingredient);
        }
    }

}
