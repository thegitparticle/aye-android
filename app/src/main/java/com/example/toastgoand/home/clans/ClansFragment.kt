package com.example.toastgoand.home.clans

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.getValue
import androidx.compose.onCommit
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.ui.foundation.VerticalScroller
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.setValue
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.compose.runtime.livedata.observeAsState
import androidx.ui.temputils.delay
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.dummy.Dummyclans
import com.example.toastgoand.home.clans.components.LiveClanItem
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.home.startclan.StartClanActivity
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.pnstuff.pushSetupClans
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.example.toastgoand.uibits.DotsLoader
import kotlinx.serialization.json.JsonNull.content
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ClansFragment : Fragment() {

    private val viewModel: ClansViewModel by viewModels {
        ClansViewModelFactory(
            (getActivity()?.getApplication() as ToastgoApplication).repositoryMyClans,
            (getActivity()?.getApplication() as ToastgoApplication).repository
        )
    }

    lateinit var prefHelper: PrefHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        prefHelper = context?.let { PrefHelper(it) }!!

        Log.i("pnnotif clans", "logging is working")
        Log.i("pnnotif clans", prefHelper.getString(Constant.FIREBASE_TOKEN).toString())

        viewModel.liveClans.observeForever {
            Log.i("pnnotif clans", "liveclans obserging worked")
            viewModel.liveClans.value?.let { it1 ->
                Log.i("pnnotif clans", "liveclans value is passed")
                prefHelper.getString(
                    Constant.FIREBASE_TOKEN
                )?.let { it2 ->
                    pushSetupClans(
                        it1, viewModel.deets.value?.user?.id.toString(), it2
                    )
                    Log.i("pnnotif clans", "function being called")
                }
            }
        }


        return inflater.inflate(R.layout.clans_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {

                @RequiresApi(Build.VERSION_CODES.O)
                @Composable
                fun MyClanItem(myclan: MyClansDataClass) {
                    if (myclan.ongoing_frame == false) {
                        DormantClan(myclan = myclan)
                    } else {
                    }
                }

                val isRefreshing by viewModel.isRefreshing.collectAsState()

                AyeTheme {
                    val clansHere: List<MyClansDataClass> by
                    viewModel.myClans.observeAsState(listOf<MyClansDataClass>())

                    val liveClansHere: MutableList<MyClansDataClass> by
                    viewModel.liveClans.observeAsState(mutableListOf<MyClansDataClass>())

                    Surface(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .fillMaxSize()
                    ) {
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing),
                            onRefresh = { viewModel.refresh() }
                        ) {
                            LazyColumn(
                                modifier = Modifier.background(MaterialTheme.colors.background)
                            ) {
                                items(
                                    items = liveClansHere,
                                    itemContent = {
                                        LiveClanItem(
                                            myclan = it,
                                            position = liveClansHere.indexOf(it)
                                        )
                                    })
                                item {
                                    Spacer(Modifier.height(20.dp))
                                }
                                items(
                                    items = clansHere,
                                    itemContent = {
                                        MyClanItem(myclan = it)
                                    })

                                item(
                                    key = "footer",
                                    content = {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Button(
                                                onClick = {
                                                    context.startActivity(
                                                        Intent(
                                                            context,
                                                            StartClanActivity::class.java
                                                        ).apply { })
                                                },
                                                colors = ButtonDefaults.textButtonColors(
                                                    backgroundColor = MaterialTheme.colors.secondary,
                                                ),
                                                shape = RoundedCornerShape(30.dp),
                                                modifier = Modifier
                                                    .padding(vertical = 30.dp)
                                                    .height(60.dp)
                                                    .width(160.dp),
                                            ) {
                                                Text(
                                                    "start clan",
                                                    style = MaterialTheme.typography.body1,
                                                    color = MaterialTheme.colors.onSecondary
                                                )
                                            }
                                        }
                                    }
                                )
                                item {
                                    Spacer(Modifier.height(300.dp))
                                }

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