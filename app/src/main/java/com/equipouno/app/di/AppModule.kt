package com.equipouno.app.di

import com.google.firebase.firestore.FirebaseFirestore
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
        // Proporciona una instancia de FirebaseFirestore que se puede inyectar en toda la aplicación
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(firestore: FirebaseFirestore): RecipeRepository {
        // Proporciona una instancia de RecipeRepository utilizando Firestore
        return RecipeRepository(firestore)
    }
}
