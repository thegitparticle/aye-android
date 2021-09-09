package com.example.toastgoand.home.startclan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityMyProfileBinding
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.home.myprofile.MyProfileViewModel
import com.google.accompanist.appcompattheme.AppCompatTheme

class StartClanActivity : BaseActivity() {
    private lateinit var binding: ActivityStartClanBinding

    private lateinit var viewModel: StartClanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityStartClanBinding

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
        return ActivityStartClanBinding.inflate(layoutInflater)
    }
}