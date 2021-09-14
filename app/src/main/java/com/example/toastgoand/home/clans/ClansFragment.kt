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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.ui.foundation.VerticalScroller
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.dummy.Dummyclans
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.home.startclan.StartClanActivity
import com.example.toastgoand.network.myclans.MyClansDataClass
import kotlinx.serialization.json.JsonNull.content

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


                AyeTheme {
                    val clansHere: List<MyClansDataClass> by viewModel.myClans.observeAsState(listOf<MyClansDataClass>())
                    Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
                        LazyColumn(
                            modifier = Modifier.background(MaterialTheme.colors.background)
                        ) {
                            items(
                                items = clansHere,
                                itemContent = {
                                    MyClanItem(myclan = it)
                                })

                            items(
                                items = clansHere,
                                itemContent = {
                                    MyClanItem(myclan = it)
                                })

                            item(
                                key = "footer",
                                content = {
                                    Button(
                                        onClick = {
                                            context.startActivity(
                                                Intent(
                                                    context,
                                                    StartClanActivity::class.java
                                                ).apply { })
                                        }, colors = ButtonDefaults.textButtonColors(
                                            backgroundColor = MaterialTheme.colors.secondary,
                                        )
                                    ) {
                                        Text(
                                            "start clan", style = MaterialTheme.typography.body1,
                                            color = MaterialTheme.colors.onSecondary
                                        )
                                    }
                                }
                            )
                            item {
                                Spacer(Modifier.height(200.dp))
                            }

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