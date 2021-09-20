package com.example.toastgoand.uibits

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronRight
import compose.icons.feathericons.X

@Composable
fun HeaderOtherScreens (
    modifier: Modifier = Modifier,
    title: String,
    onBackIconPressed: () -> Unit = { },
) {
    ProvideWindowInsets {
        TopAppBar(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
            ,
            contentPadding = rememberInsetsPaddingValues(
                LocalWindowInsets.current.statusBars,
                applyBottom = false,
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 15.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onBackground
                    )
                    CircleIcon(iconName = FeatherIcons.X, onIconPressed = onBackIconPressed, modifier = Modifier)
                }
            },
            backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.95f),
//            elevation = 4.dp
        )
    }
}