package com.example.toastgoand.home.clans

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.dummy.Dummyclans
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme

class ClansFragment : Fragment() {

    private val viewModel: ClansViewModel by viewModels {
        ClansViewModelFactory((getActivity()?.getApplication() as ToastgoApplication).repositoryMyClans, (getActivity()?.getApplication() as ToastgoApplication).repository)
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

                    Surface(
                        color = colorResource(id = R.color.off_light_splash)
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            items(
                                items = clansHere,
                                itemContent = {
                                    MyClanItem(myclan = it)
                                })
                        }
                        Text(text = clansHere.size.toString(), style = MaterialTheme.typography.subtitle1, color = Color.Black)
                    }
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}