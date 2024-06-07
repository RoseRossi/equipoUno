package com.equipouno.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.equipouno.app.model.Recipe
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val recipesCollection = firestore.collection("recipes")

    suspend fun getAllRecipes(): List<Recipe> {
        return try {
            val snapshot = recipesCollection.get().await()
            snapshot.toObjects(Recipe::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRecipeById(id: String): Recipe? {
        return try {
            val document = recipesCollection.document(id).get().await()
            document.toObject(Recipe::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getRecipeByName(name: String): Recipe? {
        return try {
            val snapshot = recipesCollection.whereEqualTo("name", name).get().await()
            if (snapshot.documents.isNotEmpty()) {
                snapshot.documents[0].toObject(Recipe::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addRecipe(recipe: Recipe): Boolean {
        return try {
            recipesCollection.add(recipe).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateRecipe(recipe: Recipe): Boolean {
        return try {
            recipesCollection.document(recipe.id).set(recipe).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteRecipe(id: String): Boolean {
        return try {
            recipesCollection.document(id).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
