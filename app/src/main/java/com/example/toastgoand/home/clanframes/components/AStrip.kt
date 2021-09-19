package com.example.toastgoand.home.clanframes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanframes.ClanFrameDataClass

@Composable
fun AStrip (start: Int, end: Int, framesList: List<ClanFrameDataClass>,  clubName: String,
            userid: String) {

    val datesArray = mutableListOf<Int>()

    for (i in start .. end) {
        datesArray.add(i)
    }

    AyeTheme () {
        Box(Modifier.width(100.dp), contentAlignment = Alignment.TopCenter) {
            Box(
                Modifier
                    .width(
                        32.dp
                    )
                    .background(Color.Magenta.copy(0.5f))
                    .padding(50.dp)
                    .height(400.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                for (date in datesArray) {
                    ADay(date, framesList, clubName = clubName, userid = userid)
                }
            }
        }
    }
}
