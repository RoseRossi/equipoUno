package com.equipouno.app.model

data class Recipe(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val favorite: Boolean = false,  // Cambiado de isFavorite a favorite
    val description: String = ""
)
