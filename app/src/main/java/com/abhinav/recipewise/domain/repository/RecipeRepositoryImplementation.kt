package com.abhinav.recipewise.domain.repository

import com.abhinav.recipewise.data.remote.dto.RecipeDTO
import com.abhinav.recipewise.domain.repository.RecipeRepository
import com.abhinav.recipewise.data.remote.dto.RecipeAPIService

class RecipeRepositoryImplementation(private val apiService: RecipeAPIService) : RecipeRepository {

    override suspend fun getAllRecipes(): List<RecipeDTO> {
        return apiService.getAllRecipes().recipes
    }

    override suspend fun getRecipeById(id: Int): RecipeDTO {
        return apiService.getRecipeById(id)
    }
}