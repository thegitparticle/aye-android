package com.example.toastgoand.home.clanframes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityClanFramesBinding
import com.example.toastgoand.databinding.ActivityDirectHubBinding
import com.example.toastgoand.dummy.DummyDirectHub
import com.example.toastgoand.dummy.DummyFramesList
import com.example.toastgoand.home.clanframes.components.ADay
import com.example.toastgoand.home.clanframes.components.AStrip
import com.example.toastgoand.home.clans.MyClanItem
import com.example.toastgoand.home.directhub.DirectHubViewModel
import com.example.toastgoand.home.directhub.components.DirectHubUserDetails
import com.example.toastgoand.home.directhub.components.DirectHubUserMetrics
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

class ClanFramesActivity : BaseActivity() {
    private lateinit var binding: ActivityClanFramesBinding

    private lateinit var viewModel: ClanFramesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanFramesBinding

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
        return ActivityClanFramesBinding.inflate(layoutInflater)
    }

}