package com.example.toastgoand.home.clans

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.TriggerVideoMessage
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clans.components.LiveClanItem
import com.example.toastgoand.home.clans.components.StartClanButton
import com.example.toastgoand.home.startclan.StartClanActivity
import com.example.toastgoand.network.AppRoomDB
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.myclans.MyClansRepo
import com.example.toastgoand.network.pnstuff.pushSetupClans
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.example.toastgoand.splash.SplashActivity
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ClansFragment : Fragment() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppRoomDB.getDatabase(activity?.getApplication() as ToastgoApplication, applicationScope) }

    private val viewModel: ClansViewModel by viewModels {
        ClansViewModelFactory(
            MyClansRepo(database.myClansDao()),
            UserDetailsRepo(database.userDetailsDao())
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

                    Log.i("speed clans", clansHere.size.toString())

                    val liveClansHere: MutableList<MyClansDataClass> by
                    viewModel.liveClans.observeAsState(mutableListOf<MyClansDataClass>())

                    Log.i("speed live clans", liveClansHere.size.toString())

                    val contextHere = LocalContext.current

                    Surface(
                        modifier = Modifier
                            .background(AyeTheme.colors.uiBackground)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing),
                            onRefresh = { viewModel.refresh() }
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .background(AyeTheme.colors.uiBackground)
                                    .fillMaxHeight()
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
                                        StartClanButton()
                                        Spacer(Modifier.height(20.dp))
                                        TriggerVideoMessage()
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