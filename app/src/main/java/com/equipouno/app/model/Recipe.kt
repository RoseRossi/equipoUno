package com.equipouno.app.model

data class Recipe(
    val id: String = "", // ID asignado por Firestore
    val name: String = "",
    val type: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val isFavorite: Boolean = false,
    val description: String = ""
)
