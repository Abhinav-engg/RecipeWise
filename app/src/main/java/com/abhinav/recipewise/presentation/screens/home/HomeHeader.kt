package com.abhinav.recipewise.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhinav.recipewise.ui.theme.Orange
@Preview(showBackground = true)
@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .background(color = Orange.copy(alpha = 0.2f))
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = Orange
                ),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ){
            Icon(
                Icons.Default.RestaurantMenu,
                contentDescription = "Restaurant Menu Icon",
                tint = Color.White

            )
        }

        Column{
            Text(
                text = "Welcome to RecipeWise!",
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(4.dp))



            Text(
                text = "Discover delicious recipes and cooking tips.",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}