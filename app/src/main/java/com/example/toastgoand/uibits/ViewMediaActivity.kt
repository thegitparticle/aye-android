package com.example.toastgoand.uibits

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityViewMediaBinding
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

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
                val context = LocalContext.current

                val imageLoader = ImageLoader.Builder(context)
                    .componentRegistry {
                        if (Build.VERSION.SDK_INT >= 28) {
                            add(ImageDecoderDecoder(context))
                        } else {
                            add(GifDecoder())
                        }
                    }
                    .build()

                CoilImage(
                    imageModel = imageLink, // URL of the animated images.
                    imageLoader = imageLoader,
                    shimmerParams = ShimmerParams(
                        baseColor = AyeTheme.colors.uiSurface,
                        highlightColor = AyeTheme.colors.uiBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                }
        }

//        haulerView.setOnDragDismissedListener {
//            finish() // finish activity when dismissed
//        }
    }

    override fun binding(): ViewBinding {
        return ActivityViewMediaBinding.inflate(layoutInflater)
    }
}
