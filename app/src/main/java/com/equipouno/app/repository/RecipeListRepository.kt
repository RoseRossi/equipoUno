package com.equipouno.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.equipouno.app.model.Recipe
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecipeListRepository {
    private val db = Firebase.firestore

    fun getRecipes(type_: String): LiveData<List<Recipe>> {
        val recipesLiveData = MutableLiveData<List<Recipe>>()

        val recipesCollection = db.collection("recipes")
        recipesCollection.whereEqualTo("type", type_)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val recipesList = mutableListOf<Recipe>()
                for (document in querySnapshot.documents) {
                    val recipe = document.toObject(Recipe::class.java)
                    recipe?.let { recipesList.add(it) }
                }
                recipesLiveData.postValue(recipesList)
            }
            .addOnFailureListener { exception ->
                recipesLiveData.postValue(emptyList())
            }

        return recipesLiveData
    }

    // Nueva función para obtener recetas favoritas
    fun getFavoriteRecipes(): LiveData<List<Recipe>> {
        val recipesLiveData = MutableLiveData<List<Recipe>>()

        val recipesCollection = db.collection("recipes")
        recipesCollection.whereEqualTo("favorite", true)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val recipesList = mutableListOf<Recipe>()
                for (document in querySnapshot.documents) {
                    val recipe = document.toObject(Recipe::class.java)
                    recipe?.let { recipesList.add(it) }
                }
                recipesLiveData.postValue(recipesList)
            }
            .addOnFailureListener { exception ->
                recipesLiveData.postValue(emptyList())
            }

        return recipesLiveData
    }
}
