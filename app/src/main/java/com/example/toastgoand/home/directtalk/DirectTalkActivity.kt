package com.example.toastgoand.home.directtalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.databinding.ActivityDirectTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clantalk.ClanTalkViewModel
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
                ClanMetrics(clanHub = DummyClanHub.clanHub)
                val members = DummyClanHub.clanHub.users
                Surface() {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(
                            items = members,
                            itemContent = {
                                UsersListItem(user = it)
                            })
                    }
                }
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
        return ActivityDirectTalkBinding.inflate(layoutInflater)
    }
}