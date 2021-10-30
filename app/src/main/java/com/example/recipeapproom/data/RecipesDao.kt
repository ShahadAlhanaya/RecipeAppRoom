package com.example.recipeapproom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
    interface RecipesDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addRecipe(recipe: Recipe)

        @Update
        suspend fun updateRecipe(recipe: Recipe)

        @Delete
        suspend fun deleteRecipe(recipe: Recipe)

        @Query("SELECT * FROM recipes_table ORDER BY ID ASC")
        fun getAllRecipes(): LiveData<List<Recipe>>

    }