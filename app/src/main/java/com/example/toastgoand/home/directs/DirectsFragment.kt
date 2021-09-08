package com.example.toastgoand.home.directs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.databinding.DirectsFragmentBinding
import com.example.toastgoand.dummy.DummyDirects
import com.example.toastgoand.dummy.DummyNudge
import com.example.toastgoand.dummy.Dummyclans
import com.example.toastgoand.home.clans.ClansViewModel
import com.example.toastgoand.home.clans.MyClanItem
import com.example.toastgoand.home.directs.components.DirectItem
import com.example.toastgoand.home.directs.components.NudgeToItem

class DirectsFragment : Fragment() {

    private lateinit var viewModel: DirectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )

            setContent {
                MaterialTheme {
                    val directs = remember { DummyDirects.myDirects }
                    val nudges = remember {DummyNudge.nudgeItems}

                    Surface (
                        color = colorResource(id = R.color.off_light_splash)
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            items(
                                items = directs,
                                itemContent = {
                                    DirectItem(directItem = it)
                                })
                        }
                        Text(text = "more friends", style = MaterialTheme.typography.subtitle1, color = Color.Black)
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            items(
                                items = nudges,
                                itemContent = {
                                    NudgeToItem(nudgeItem = it)
                                })
                        }
                    }

                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DirectsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}