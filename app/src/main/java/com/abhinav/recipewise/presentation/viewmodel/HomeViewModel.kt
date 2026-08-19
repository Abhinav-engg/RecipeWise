package com.abhinav.recipewise.presentation.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhinav.recipewise.data.remote.KtorClient
import com.abhinav.recipewise.data.remote.dto.RecipeAPIService
import com.abhinav.recipewise.data.remote.dto.RecipeDTO
import com.abhinav.recipewise.domain.repository.RecipeRepository
import com.abhinav.recipewise.domain.repository.RecipeRepositoryImplementation
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val recipeRepository: RecipeRepository = RecipeRepositoryImplementation(
        apiService = RecipeAPIService(
            KtorClient.client
        )
    )


    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipes by mutableStateOf<List<RecipeDTO>>(emptyList())
        private set

    var selectedCategory by mutableStateOf("ALL")
        private set

    var categories by mutableStateOf<List<String>>(listOf("ALL"))


    private var allRecipes: List<RecipeDTO> = emptyList()

    init{
        fetchRecipes()
    }


    fun fetchRecipes() {
        isLoading = true
        errorMessage = null


        viewModelScope.launch {

            try{
                val result = recipeRepository.getAllRecipes()
                allRecipes = result

                val cuisines = result.map { it.cuisine }.distinct().sorted()
                categories = listOf("ALL") + cuisines

                applyFilters()

            }catch(e: Exception){
                errorMessage = e.message ?: "An unexpected error occurred"
            }finally {
                isLoading = false
            }
        }
    }

    fun OnCategorySelected(category: String) {
        selectedCategory = category
        applyFilters()
    }

    private fun applyFilters(){
        recipes = if(selectedCategory == "ALL"){
            allRecipes
        } else {
            allRecipes.filter { it.cuisine == selectedCategory }
        }
    }
}