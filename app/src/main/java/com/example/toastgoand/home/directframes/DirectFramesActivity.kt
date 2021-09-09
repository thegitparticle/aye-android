package com.example.toastgoand.home.directframes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityClanFramesBinding
import com.example.toastgoand.databinding.ActivityDirectFramesBinding
import com.example.toastgoand.home.clanframes.ClanFramesViewModel
import com.example.toastgoand.home.clanframes.components.AStrip
import com.google.accompanist.appcompattheme.AppCompatTheme

class DirectFramesActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectFramesBinding

    private lateinit var viewModel: DirectFrameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectFramesBinding

        setContent {
            AppCompatTheme() {
                Surface(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    color = colorResource(id = R.color.off_light_splash)
                ) {
                    Row (horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                        AStrip(start = 1, end = 10)
                        AStrip(start = 11, end = 20)
                        AStrip(start = 21, end = 31)
                    }
                }
            }
        }
    }


    override fun binding(): ViewBinding {
        return ActivityDirectFramesBinding.inflate(layoutInflater)
    }
}