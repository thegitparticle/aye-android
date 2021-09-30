package com.example.toastgoand.uibits

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.toastgoand.composestyle.AyeTheme

@Composable
fun LoaderDialog(isDialogOpen: MutableState<Boolean>) {

    AyeTheme() {
        if (isDialogOpen.value) {
            Dialog(onDismissRequest = { isDialogOpen.value = false }) {
                Surface(
                    modifier = Modifier
                        .width(300.dp)
                        .height(450.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(5.dp),
                    color = Color.White.copy(0.0f)
                ) {
                    Column(
                        modifier = Modifier.padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

            }
        }
    }
}