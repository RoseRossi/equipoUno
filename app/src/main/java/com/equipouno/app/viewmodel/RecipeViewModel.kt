package com.equipouno.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equipouno.app.model.Recipe
import com.equipouno.app.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    fun loadRecipes() {
        viewModelScope.launch {
            _recipes.value = recipeRepository.getAllRecipes()
        }
    }

    fun getRecipeById(id: String): LiveData<Recipe?> {
        val recipe = MutableLiveData<Recipe?>()
        viewModelScope.launch {
            recipe.value = recipeRepository.getRecipeById(id)
        }
        return recipe
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            val isSuccess = recipeRepository.addRecipe(recipe)
            if (isSuccess) {
                loadRecipes() // Recargar las recetas después de agregar
            }
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch {
            val isSuccess = recipeRepository.updateRecipe(recipe)
            if (isSuccess) {
                loadRecipes() // Recargar las recetas después de actualizar
            }
        }
    }

    fun deleteRecipe(id: String) {
        viewModelScope.launch {
            val isSuccess = recipeRepository.deleteRecipe(id)
            if (isSuccess) {
                loadRecipes() // Recargar las recetas después de eliminar
            }
        }
    }
}
