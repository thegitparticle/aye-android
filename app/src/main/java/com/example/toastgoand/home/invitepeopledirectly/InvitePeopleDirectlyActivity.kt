package com.example.toastgoand.home.invitepeopledirectly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityInvitePeopleDirectlyBinding
import com.example.toastgoand.databinding.ActivityViewOldFrameBinding
import com.example.toastgoand.home.viewoldframe.ViewOldViewModel
import com.google.accompanist.appcompattheme.AppCompatTheme

class InvitePeopleDirectlyActivity : BaseActivity() {
    private lateinit var binding: ActivityInvitePeopleDirectlyBinding

    private lateinit var viewModel: ViewOldViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityInvitePeopleDirectlyBinding

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
        return ActivityInvitePeopleDirectlyBinding.inflate(layoutInflater)
    }
}