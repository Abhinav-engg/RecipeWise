package com.abhinav.recipewise.presentation.screens.recipe_details

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String,
    onBackClick: () -> Unit,
    icon: ImageVector
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {

            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Back"
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            containerColor = Color(0xFF6200EE) // Example color
        )
    )
}
