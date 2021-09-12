package com.example.toastgoand.home.clanframes

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityClanFramesBinding
import com.example.toastgoand.home.clanframes.components.AStrip
import com.example.toastgoand.home.clanhub.ClanHubActivity
import com.google.accompanist.appcompattheme.AppCompatTheme

class ClanFramesActivity : BaseActivity() {
    private lateinit var binding: ActivityClanFramesBinding

    private lateinit var viewModel: ClanFramesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanFramesBinding

        setContent {
            AppCompatTheme() {
                val clubName = intent.getStringExtra("clubName")
                val context = LocalContext.current

                TopAppBar(title = {
                    if (clubName != null) {
                        Text(clubName, style = MaterialTheme.typography.subtitle1, color = Color.Black)
                    }
                },
                    navigationIcon = {
                        IconButton(onClick = {
                            context.startActivity(Intent(context, ClanHubActivity::class.java).apply {
                                putExtra("clubName", clubName)
                            }, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                        }) {
                            Icon(imageVector = Icons.Filled.Star  , contentDescription = "Hub")
                        }
                    })
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
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
        return ActivityClanFramesBinding.inflate(layoutInflater)
    }

}