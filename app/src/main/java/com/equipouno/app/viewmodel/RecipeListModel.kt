package com.equipouno.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.equipouno.app.model.Recipe
import com.equipouno.app.repository.RecipeListRepository

class RecipeListModel : ViewModel() {
    private val repository = RecipeListRepository()

    fun getRecipes(type: String): LiveData<List<Recipe>> {
        return repository.getRecipes(type)
    }
}