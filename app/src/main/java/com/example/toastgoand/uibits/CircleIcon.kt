package com.example.toastgoand.uibits

import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.composestyle.TheAyeColors
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronRight

@Composable
fun CircleIcon(onIconPressed: () -> Unit = { }, iconName: ImageVector, modifier: Modifier) {

    @Composable
    fun BackgroundPlusIcon(shape: Shape) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .background(AyeTheme.colors.iconBackground.copy(0.0f))
                .clickable(onClick = onIconPressed),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(shape)
                    .background(AyeTheme.colors.iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    iconName,
                    "circle icon",
                    tint = AyeTheme.colors.iconVector,
                    modifier = Modifier.size(17.dp),
                )
            }
        }
    }

    BackgroundPlusIcon(shape = CircleShape)
}

@Composable
fun CircleIconSmall(onIconPressed: () -> Unit = { }, iconName: ImageVector, modifier: Modifier) {

    @Composable
    fun BackgroundPlusIcon(shape: Shape) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .background(AyeTheme.colors.iconBackground.copy(0.0f))
                .clickable(onClick = onIconPressed),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(shape)
                    .background(AyeTheme.colors.iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    iconName,
                    "circle icon small",
                    tint = AyeTheme.colors.iconVector,
                    modifier = Modifier.size(17.dp),
                )
            }
        }
    }

    BackgroundPlusIcon(shape = CircleShape)
}

@Composable
fun CircleIconCustomColorSize(
    onIconPressed: () -> Unit = { },
    iconName: ImageVector,
    modifier: Modifier,
    color: Color,
    colorBg: Color,
    size: Dp
) {

    @Composable
    fun BackgroundPlusIcon(shape: Shape) {
        Box(
            modifier
                .width((size * 2))
                .background(color.copy(0.0f))
                ,
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(shape)
                    .background(color = colorBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    iconName,
                    "circle icon",
                    tint = color,
                    modifier = Modifier.size(size / 2),
                )
            }
        }
    }

    BackgroundPlusIcon(shape = CircleShape)
}