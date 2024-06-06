package com.equipouno.app.di

import com.google.firebase.firestore.FirebaseFirestore
import com.equipouno.app.api.ApiClient
import com.equipouno.app.api.ApiService
import com.equipouno.app.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient.apiService
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(apiService: ApiService, firestore: FirebaseFirestore): RecipeRepository {
        return RecipeRepository(apiService, firestore)
    }
}
