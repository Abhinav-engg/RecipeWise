package com.abhinav.recipewise.presentation.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.abhinav.recipewise.presentation.viewmodel.Recipe_DetailsViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abhinav.recipewise.data.remote.dto.RecipeDTO
import com.abhinav.recipewise.presentation.screens.recipe_details.DetailSection
import com.abhinav.recipewise.presentation.screens.recipe_details.InfoChip
import com.abhinav.recipewise.presentation.screens.recipe_details.StatItem
import com.abhinav.recipewise.ui.theme.Orange

@Composable
fun RecipeDetailScreen(



    recipeId: Int,
    onBack: () -> Unit
) {
    val viewModel: Recipe_DetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage
    val recipe = viewModel.recipe

    LaunchedEffect(recipeId) {
        viewModel.fetchRecipeDetail(recipeId)
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = error, color = Color.Red)
        }
        return
    }

    val details = recipe ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = details.image,
            contentDescription = details.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        DetailSection(
            title = "Recipe Details",
            icon = Icons.Default.RemoveRedEye
        ) {
            Text(
                text = details.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoChip(
                    label = details.cuisine,
                    icon = Icons.Default.Public
                )
                InfoChip(
                    label = details.difficulty,
                    icon = Icons.Default.Star
                )
                InfoChip(
                    label = details.mealType.firstOrNull() ?: "",
                    icon = Icons.Default.Bolt
                )
            }
        }

        DetailSection(
            title = "At a Glance",
            icon = Icons.Default.Timer
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem(
                    label = "Prep",
                    value = "${details.prepTimeMinutes}m",
                    icon = Icons.Default.Schedule
                )
                VerticalDivider(
                    modifier = Modifier.height(36.dp),
                    thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.2f)
                )
                StatItem(
                    label = "Cook",
                    value = "${details.cookTimeMinutes}m",
                    icon = Icons.Default.Whatshot
                )
                VerticalDivider(
                    modifier = Modifier.height(36.dp),
                    thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.2f)
                )
                StatItem(
                    label = "Serves",
                    value = "${details.servings}",
                    icon = Icons.Default.Egg
                )
                VerticalDivider(
                    modifier = Modifier.height(36.dp),
                    thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.2f)
                )
                StatItem(
                    label = "Calories",
                    value = "${details.caloriesPerServing}",
                    icon = Icons.Default.LocalFireDepartment
                )
            }
        }

        DetailSection(
            title = "Ingredients",
            icon = Icons.Default.Restaurant
        ) {
            details.ingredients.forEach { ingredient ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Orange)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = ingredient,
                        color = Color.DarkGray.copy(alpha = 0.85f)
                    )
                }
            }
        }

        DetailSection(
            title = "Instructions",
            icon = Icons.Default.Info
        ) {
            details.instructions.forEachIndexed { index, instruction ->
                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Orange),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = (index + 1).toString(),
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = instruction,
                        color = Color.DarkGray.copy(alpha = 0.85f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}