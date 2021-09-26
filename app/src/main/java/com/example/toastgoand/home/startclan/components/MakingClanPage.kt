package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun MakingClanPage(backHandle: Unit, navController: NavController) {

    AyeTheme() {

        ProvideWindowInsets() {
            Scaffold(
                topBar = {
                    HeaderOtherScreens(
                        modifier = Modifier.fillMaxWidth(),
                        title = "",
                        onBackIconPressed = { backHandle }
                    )
                }
            ) { contentPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("making the clan")

                }
            }
        }
    }
}