package com.example.toastgoand.home.directframes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.directframes.DirectFrameDataClass

@Composable
fun AStripDirect(start: Int, end: Int, framesList: List<DirectFrameDataClass>) {

    val datesArray = mutableListOf<Int>()

    for (i in start .. end) {
        datesArray.add(i)
    }

    AyeTheme () {
        Box() {
            Box(
                Modifier
                    .width(
                        50.dp
                    )
                    .background(Color.Blue)
                    .padding(50.dp)
                    .height(400.dp)
            )
            Column() {
                for (date in datesArray) {
                    ADayDirect(date, framesList = framesList)
                }
            }
        }
    }
}