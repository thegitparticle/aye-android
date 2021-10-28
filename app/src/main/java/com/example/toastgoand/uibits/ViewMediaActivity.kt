package com.example.toastgoand.uibits

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import coil.compose.rememberImagePainter
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityViewMediaBinding
import app.futured.hauler.setOnDragDismissedListener
import kotlinx.android.synthetic.main.activity_view_media.*

class ViewMediaActivity : BaseActivity() {
    private lateinit var binding: ActivityViewMediaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityViewMediaBinding

        val imageLink = intent.getStringExtra("imagelink")

        val composeView = binding.composeView

        composeView.setContent {
            AyeTheme() {
                    val painter = rememberImagePainter(data = imageLink)
                    Image(
                        painter = painter,
                        contentDescription = "view media full screen",
                        contentScale = ContentScale.Fit ,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }
        }

        haulerView.setOnDragDismissedListener {
            finish() // finish activity when dismissed
        }
    }

    override fun binding(): ViewBinding {
        return ActivityViewMediaBinding.inflate(layoutInflater)
    }
}