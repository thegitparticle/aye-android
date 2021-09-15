package com.example.toastgoand.home.directtalk.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun TextInputDirect (modifier: Modifier) {
    val typedText = remember { mutableStateOf(TextFieldValue()) }
    ProvideWindowInsets() {
        OutlinedTextField(
            value = typedText.value,
            onValueChange = { typedText.value = it },
            placeholder = { Text(text = "type fun stuff...") },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .navigationBarsWithImePadding()
        )
    }
}