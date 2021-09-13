package com.example.toastgoand.home.directs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.databinding.DirectsFragmentBinding
import com.example.toastgoand.dummy.DummyDirects
import com.example.toastgoand.dummy.DummyNudge
import com.example.toastgoand.dummy.Dummyclans
import com.example.toastgoand.home.clans.ClansViewModel
import com.example.toastgoand.home.clans.MyClanItem
import com.example.toastgoand.home.directs.components.DirectItem
import com.example.toastgoand.home.directs.components.NudgeToItem
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme

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

                AppCompatTheme {
                    val directsHere: List<MyDirectsDataClass> by viewModel.myDirects.observeAsState(
                        listOf<MyDirectsDataClass>()
                    )
                    val nudgeToHere: List<NudgeToDataClass> by viewModel.nudgeTo.observeAsState(
                        listOf<NudgeToDataClass>()
                    )
                    Surface(
                        color = colorResource(id = R.color.off_light_splash)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(
                                    items = directsHere,
                                    itemContent = {
                                        DirectItem(directItem = it)
                                    })
                                items(
                                    items = nudgeToHere,
                                    itemContent = {
                                        NudgeToItem(nudgeItem = it)
                                    })
                            }
                            Text(
                                text = "more friends",
                                style = MaterialTheme.typography.subtitle1,
                                color = Color.Black
                            )
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