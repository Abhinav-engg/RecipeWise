package com.abhinav.recipewise.domain.repository

import com.abhinav.recipewise.data.remote.dto.RecipeDTO

interface RecipeRepository {

    suspend fun getAllRecipes(): List<RecipeDTO>

    suspend fun getRecipeById(id: Int): RecipeDTO

}