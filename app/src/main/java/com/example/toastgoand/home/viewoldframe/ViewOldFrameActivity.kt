package com.example.toastgoand.home.viewoldframe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.databinding.ActivityViewOldFrameBinding
import com.example.toastgoand.home.startclan.StartClanViewModel

class ViewOldFrameActivity : BaseActivity() {
    private lateinit var binding: ActivityViewOldFrameBinding

    private lateinit var viewModel: ViewOldViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityViewOldFrameBinding

        setContent {
            AyeTheme {
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
        return ActivityViewOldFrameBinding.inflate(layoutInflater)
    }
}