package com.equipouno.app.repository

import com.equipouno.app.api.ApiService
import com.equipouno.app.api.RecipeResponse
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiService: ApiService,
    firestore: FirebaseFirestore
) {

    suspend fun getRecipe(recipeId: String, appId: String, appKey: String): RecipeResponse? {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getRecipe(recipeId, appId, appKey)
            } catch (e: Exception) {
                null
            }
        }
    }
}
