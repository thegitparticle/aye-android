package com.example.toastgoand.uibits

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronRight

@Composable
fun CircleIcon (onIconPressed: () -> Unit = { }, iconName: ImageVector, modifier: Modifier) {
    
    @Composable
    fun BackgroundPlusIcon (shape: Shape) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(shape)
                .background(MaterialTheme.colors.onBackground.copy(0.1f))
                .alpha(0.1f)
                .clickable(onClick = onIconPressed),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = iconName,
                contentDescription = "last month",
                modifier = Modifier.size(17.dp)
            )
        }
    }
    
    BackgroundPlusIcon(shape = CircleShape)
}