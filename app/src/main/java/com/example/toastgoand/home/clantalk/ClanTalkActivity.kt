package com.example.toastgoand.home.clantalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityClanHubBinding
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanhub.ClanHubViewModel
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.uibits.TopHeaderPlayScreens
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.accompanist.insets.statusBarsPadding
import com.google.android.material.composethemeadapter.MdcTheme

class ClanTalkActivity : BaseActivity() {

    private lateinit var binding: ActivityClanTalkBinding

    private lateinit var viewModel: ClanTalkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanTalkBinding

        setContent {
            AppCompatTheme {
                ClanMetrics(clanHub = DummyClanHub.clanHub)
                val members = DummyClanHub.clanHub.users
                val clubName = intent.getStringExtra("clubName")
                val context = LocalContext.current

                Column(modifier = Modifier.fillMaxSize()) {
                    TopHeaderPlayScreens(
                        modifier = Modifier.fillMaxWidth(),
                        onLeftIconPressed = {
                            context.startActivity(
                                        Intent(
                                            context,
                                            ClanFramesActivity::class.java
                                        ).apply {
                                            putExtra("clubName", clubName)
                                        })
                        },
                        title = {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Channel name
                                if (clubName != null) {
                                    Text(
                                        text = clubName,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                }
                            }
                        },
                    )

                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        items(
                            items = members,
                            itemContent = {
                                UsersListItem(user = it)
                            })
                    }
                }

            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityClanTalkBinding.inflate(layoutInflater)
    }
}