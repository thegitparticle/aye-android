package com.example.toastgoand.home.clans

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.dummy.Dummyclans
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.home.startclan.StartClanActivity
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme

class ClansFragment : Fragment() {

    private val viewModel: ClansViewModel by viewModels {
        ClansViewModelFactory(
            (getActivity()?.getApplication() as ToastgoApplication).repositoryMyClans,
            (getActivity()?.getApplication() as ToastgoApplication).repository
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.clans_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {

                AppCompatTheme {
                    val clansHere: List<MyClansDataClass> by viewModel.myClans.observeAsState(listOf<MyClansDataClass>())

                    Column(
                        Modifier.background(Color.Magenta).fillMaxHeight().padding(horizontal = 8.dp, vertical = 60.dp)
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                            modifier = Modifier.fillMaxHeight(),
                        ) {
                            items(
                                items = clansHere,
                                itemContent = {
                                    MyClanItem(myclan = it)
                                })
                        }
                        Button(onClick = {
                            context.startActivity(Intent(context, StartClanActivity::class.java).apply {
                            })
                        }, colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Green,
                        )) {
                            Text("start clan")
                        }
                    }
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}