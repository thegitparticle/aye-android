package com.example.toastgoand.home.clanhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityClanHubBinding
import com.example.toastgoand.databinding.ActivityLandingBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.dummy.DummyClanHub.clanHub
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clans.ClansViewModel
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

class ClanHubActivity : BaseActivity() {

    private lateinit var binding: ActivityClanHubBinding

    private lateinit var viewModel: ClanHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanHubBinding

        setContent {
            AppCompatTheme {
                ClanMetrics(clanHub = clanHub)
                val members = clanHub.users
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
               Row () {
                   Button(onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                       backgroundColor = Color.Blue
                   )) {
                       Text("add friends")
                   }
                   Button(onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                       backgroundColor = Color.Black
                   )) {
                       Text("invite friends")
                   }
               }
                Button(onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Red
                )) {
                    Text("quit clan")
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityClanHubBinding.inflate(layoutInflater)
    }
}