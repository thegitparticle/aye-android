package com.example.toastgoand.home.directs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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

class DirectsFragment : Fragment() {

    private val viewModel: DirectsViewModel by viewModels {
        DirectsViewModelFactory(
            (getActivity()?.getApplication() as ToastgoApplication).repositoryMyDirects,
            (getActivity()?.getApplication() as ToastgoApplication).repository,
            (getActivity()?.getApplication() as ToastgoApplication).repositoryNudgeTo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.directs_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {

                AyeTheme {
                    val directsHere: List<MyDirectsDataClass> by viewModel.myDirects.observeAsState(
                        listOf<MyDirectsDataClass>()
                    )
                    val nudgeToHere: List<NudgeToDataClass> by viewModel.nudgeTo.observeAsState(
                        listOf<NudgeToDataClass>()
                    )
                    Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
                        LazyColumn(
                            modifier = Modifier.background(MaterialTheme.colors.background)
                        ) {
                            items(
                                items = directsHere,
                                itemContent = {
                                    DirectItem(directItem = it)
                                })
                            item {
                                Spacer(Modifier.height(20.dp))
                                Divider(
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier.alpha(
                                        0.1F
                                    )
                                    , thickness = 1.dp
                                )
                                Spacer(Modifier.height(20.dp))
                            }
                            items(
                                items = nudgeToHere,
                                itemContent = {
                                    NudgeToItem(it)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}