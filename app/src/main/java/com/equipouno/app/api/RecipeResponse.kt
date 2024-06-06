package com.equipouno.app.api

data class RecipeResponse(
    val id: String,
    val name: String,
    val ingredientLines: List<String>,
    val flavors: Flavors?,
    val nutritionEstimates: List<NutritionEstimate>?,
    val images: List<Image>,
    val source: Source
)

data class Flavors(
    val piquant: Float?,
    val bitter: Float?,
    val sweet: Float?,
    val meaty: Float?,
    val salty: Float?,
    val sour: Float?
)

data class NutritionEstimate(
    val attribute: String,
    val description: String,
    val value: Float,
    val unit: Unit
)

data class Unit(
    val name: String,
    val abbreviation: String,
    val plural: String,
    val pluralAbbreviation: String
)

data class Image(
    val hostedLargeUrl: String,
    val hostedMediumUrl: String,
    val hostedSmallUrl: String
)

data class Source(
    val sourceRecipeUrl: String,
    val sourceSiteUrl: String,
    val sourceDisplayName: String
)
