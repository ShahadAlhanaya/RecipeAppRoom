package com.example.recipeapproom.data
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    val  getAllRecipes : LiveData<List<Recipe>>
    private val repository : RecipesRepository

    init {
        val noteDao = RecipesDB.getDatabase(application).recipesDao()
        repository = RecipesRepository(noteDao)
        getAllRecipes = repository.getAllRecipes
    }

    fun addRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipe(recipe)
        }
    }

}