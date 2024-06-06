package com.equipouno.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.equipouno.app.api.RecipeResponse
import com.equipouno.app.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipe = MutableLiveData<RecipeResponse?>()
    val recipe: LiveData<RecipeResponse?> get() = _recipe

    fun loadRecipe(recipeId: String, appId: String, appKey: String) {
        viewModelScope.launch {
            _recipe.value = recipeRepository.getRecipe(recipeId, appId, appKey)
        }
    }
}
