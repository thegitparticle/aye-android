package com.example.toastgoand.uibits

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SqaureRoundedIcon(iconName: ImageVector, color: Color, modifier: Modifier) {

    @Composable
    fun BackgroundPlusIcon(shape: Shape) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape)
                .background(color.copy(0.1f))
                .alpha(0.1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = iconName,
                contentDescription = "last month",
                modifier = Modifier.size(17.dp)
            )
        }
    }

    BackgroundPlusIcon(shape = RoundedCornerShape(10.dp))
}