package com.abhinav.recipewise.presentation.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.recipewise.data.remote.KtorClient
import com.abhinav.recipewise.data.remote.dto.RecipeAPIService
import com.abhinav.recipewise.data.remote.dto.RecipeDTO
import com.abhinav.recipewise.domain.repository.RecipeRepository
import com.abhinav.recipewise.domain.repository.RecipeRepositoryImplementation
import kotlinx.coroutines.launch

class Recipe_DetailsViewModel : ViewModel() {

    private val repository: RecipeRepository =
        RecipeRepositoryImplementation(apiService = RecipeAPIService(KtorClient.client))

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipe by mutableStateOf<RecipeDTO?>(null)
        private set

    fun fetchRecipeDetail(id: Int) {
        isLoading = true
        errorMessage = null

        try {
            viewModelScope.launch {
                recipe = repository.getRecipeById(id)
            }
        } catch (e: Exception) {
            errorMessage = e.message ?: "An unexpected error occurred"
        } finally {
            isLoading = false
        }
    }
}