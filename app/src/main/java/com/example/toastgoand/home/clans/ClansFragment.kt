package com.example.toastgoand.home.clans

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ClansFragmentBinding
import com.example.toastgoand.dummy.Dummyclans
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.lightColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource

class ClansFragment : Fragment() {

    private lateinit var viewModel: ClansViewModel

    @RequiresApi(Build.VERSION_CODES.O)
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
                    val puppies = remember { Dummyclans.myClans }
                    Surface (
                        color = colorResource(id = R.color.off_light_splash)
                            ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                    modifier = Modifier.fillMaxHeight()
                        ) {
                            items(
                                items = puppies,
                                itemContent = {
                                    MyClanItem(myclan = it)
                                })
                        }
                    }

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClansViewModel::class.java)
        // TODO: Use the ViewModel
    }

}