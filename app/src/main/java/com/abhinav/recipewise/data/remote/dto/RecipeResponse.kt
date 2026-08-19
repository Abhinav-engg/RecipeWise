package com.abhinav.recipewise.data.remote.dto
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val recipes: List<RecipeDTO>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
