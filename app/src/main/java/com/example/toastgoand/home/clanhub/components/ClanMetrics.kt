package com.example.toastgoand.home.clanhub.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.toastgoand.home.clanhub.ClanDetailsDataClass
import com.example.toastgoand.home.clanhub.ClanHubDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun ClanMetrics (clanHub: ClanDetailsDataClass) {
    AppCompatTheme() {
        Box(contentAlignment= Alignment.Center,
            modifier = Modifier
                .background(Color.Black, shape = CircleShape)
                .layout() { measurable, constraints ->
                    // Measure the composable
                    val placeable = measurable.measure(constraints)

                    //get the current max dimension to assign width=height
                    val currentHeight = placeable.height
                    var heightCircle = currentHeight
                    if (placeable.width > heightCircle)
                        heightCircle = placeable.width

                    //assign the dimension and the center position
                    layout(heightCircle, heightCircle) {
                        // Where the composable gets placed
                        placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                    }
                }) {

            Text(
                text = "${clanHub.frames_total}",
                textAlign = TextAlign.Center,
                color = Color.Magenta,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(4.dp)
                    .defaultMinSize(15.dp) //Use a min size for short text.
            )
        }
    }
}