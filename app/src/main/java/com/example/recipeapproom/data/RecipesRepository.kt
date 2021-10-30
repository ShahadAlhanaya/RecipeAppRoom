package com.example.recipeapproom.data

import androidx.lifecycle.LiveData

class RecipesRepository (private val recipesDao: RecipesDao){

    val getAllRecipes: LiveData<List<Recipe>> = recipesDao.getAllRecipes()

    suspend fun addRecipe(recipe: Recipe){
        recipesDao.addRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe){
        recipesDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe){
        recipesDao.deleteRecipe(recipe)
    }
}