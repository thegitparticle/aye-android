package com.example.toastgoand.home.clanframes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanframes.ClanFrameDataClass

@Composable
fun AStrip (start: Int, end: Int, framesList: List<ClanFrameDataClass>) {

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
                    ADay(date, framesList)
                }
            }
        }
    }
}