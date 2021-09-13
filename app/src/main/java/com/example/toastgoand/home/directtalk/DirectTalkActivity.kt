package com.example.toastgoand.home.directtalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.databinding.ActivityDirectTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clantalk.ClanTalkViewModel
import com.example.toastgoand.home.directframes.DirectFramesActivity
import com.example.toastgoand.uibits.TopHeaderPlayScreens
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

class DirectTalkActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectTalkBinding

    private lateinit var viewModel: DirectTalkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectTalkBinding

        setContent {
            AppCompatTheme {
                val members = DummyClanHub.clanHub.users
                val otherName = intent.getStringExtra("otherName")
                val context = LocalContext.current

                Column(modifier = Modifier.fillMaxSize()) {
                    TopHeaderPlayScreens(
                        modifier = Modifier.fillMaxWidth(),
                        onLeftIconPressed = {
                            context.startActivity(
                                Intent(
                                    context,
                                    DirectFramesActivity::class.java
                                ).apply {
                                    putExtra("otherName", otherName)
                                })
                        },
                        title = {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Channel name
                                if (otherName != null) {
                                    Text(
                                        text = otherName,
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
        return ActivityDirectTalkBinding.inflate(layoutInflater)
    }
}