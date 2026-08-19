package com.abhinav.recipewise.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abhinav.recipewise.presentation.screens.home.HomeScreen
import com.abhinav.recipewise.data.remote.dto.RecipeDTO
import com.abhinav.recipewise.presentation.recipe_detail.RecipeDetailScreen


@Suppress("unused")
@Composable
fun RecipeNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onRecipeClick = { recipeId ->
                    navController.navigate(RecipeDetailsRoute(recipeId))
                }
            )
        }
        composable<RecipeDetailsRoute> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<RecipeDetailsRoute>()

            RecipeDetailScreen(
                recipeId = detailRoute.recipeId,
                onBack = {
                    navController.popBackStack()
                }
            )
        //                onBack = {
//                    navController.popBackStack()


        }
    }
}

