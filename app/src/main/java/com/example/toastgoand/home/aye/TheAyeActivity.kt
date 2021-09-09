package com.example.toastgoand.home.aye

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityTheAyeBinding
import com.google.accompanist.appcompattheme.AppCompatTheme

class TheAyeActivity : BaseActivity() {
    private lateinit var binding: ActivityTheAyeBinding

    private lateinit var viewModel: TheAyeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityTheAyeBinding

        setContent {
            AppCompatTheme {
                Button(
                    onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Red
                    )
                ) {
                    Text("quit clan")
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityTheAyeBinding.inflate(layoutInflater)
    }
}