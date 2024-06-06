package com.equipouno.app.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v1/api/recipe/{recipe-id}")
    suspend fun getRecipe(
        @Path("recipe-id") recipeId: String,
        @Query("_app_id") appId: String,
        @Query("_app_key") appKey: String
    ): RecipeResponse
}
