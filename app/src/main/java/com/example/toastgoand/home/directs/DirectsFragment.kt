package com.example.toastgoand.home.directs

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.directs.components.DirectItem
import com.example.toastgoand.home.directs.components.NudgeToItem
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.uibits.GlowIndicator
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class DirectsFragment : Fragment() {

    private val viewModel: DirectsViewModel by viewModels {
        DirectsViewModelFactory(
            (getActivity()?.getApplication() as ToastgoApplication).repositoryMyDirects,
            (getActivity()?.getApplication() as ToastgoApplication).repository,
            (getActivity()?.getApplication() as ToastgoApplication).repositoryNudgeTo
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.directs_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {

                val isRefreshing by viewModel.isRefreshing.collectAsState()

                AyeTheme {
                    val directsHere: List<MyDirectsDataClass> by viewModel.myDirects.observeAsState(
                        listOf<MyDirectsDataClass>()
                    )
                    val nudgeToHere: List<NudgeToDataClass> by viewModel.nudgeTo.observeAsState(
                        listOf<NudgeToDataClass>()
                    )

                    Log.i("speed directs", directsHere.size.toString())

                    Log.i("speed nudge", nudgeToHere.size.toString())

                    Surface(modifier = Modifier.background(AyeTheme.colors.uiBackground)) {
                        SwipeRefresh(
                            state = rememberSwipeRefreshState(isRefreshing),
                            indicator = { state, trigger ->
                                GlowIndicator(
                                    swipeRefreshState = state,
                                    refreshTriggerDistance = trigger
                                )
                            },
                            onRefresh = { viewModel.refresh() }
                        ) {
                            LazyColumn(
                                modifier = Modifier.background(AyeTheme.colors.uiBackground)
                            ) {
                                item {
                                    Spacer(Modifier.height(20.dp))
                                }
                                items(
                                    items = directsHere,
                                    itemContent = {
                                        DirectItem(directItem = it)
                                    })
                                item {
                                    Spacer(Modifier.height(20.dp))
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Divider(
                                            color = AyeTheme.colors.textSecondary,
                                            modifier = Modifier
                                                .alpha(
                                                    0.1F
                                                )
                                                .fillMaxWidth(0.8f),
                                            thickness = 1.dp,
                                        )
                                    }
                                    Spacer(Modifier.height(20.dp))
                                }
                                items(
                                    items = nudgeToHere,
                                    itemContent = {
                                        viewModel.deets.value?.user?.id?.toString()
                                            ?.let { it1 -> NudgeToItem(it, currentuserid = it1) }
                                    })
                                item {
                                    Spacer(Modifier.height(200.dp))
                                }

                            }
                        }
                    }

                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.callingInitHere()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}