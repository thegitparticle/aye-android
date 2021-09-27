package com.example.toastgoand.home.directframes.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanhub.ClanDetailsDataClass
import com.example.toastgoand.home.clanhub.ClanHubViewModel
import com.example.toastgoand.home.directframes.DirectFrameDataClass
import com.example.toastgoand.home.directframes.network.DirectFramesListApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AMonthViewModel : ViewModel() {

    private val _framesListMonthly = MutableLiveData<List<DirectFrameDataClass>>()
    val framesListMonthly: LiveData<List<DirectFrameDataClass>>
        get() = _framesListMonthly

    fun getFramesListMonthlyHere(directid: String, viewMonth: Int) {
        viewModelScope.launch {
            try {
                var framesListHere = DirectFramesListApi.retrofitService.getMonthlyFramesList(
                    viewMonth.toString(),
                    directid
                )
                _framesListMonthly.value = framesListHere
                Log.i("frameslistViewModel", "api call works")
            } catch (e: Exception) {
                Log.i("frameslistViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

@Composable
fun AMonthDirect(
    viewModel: AMonthViewModel,
    directid: String,
    viewMonth: Int,
    currentMonth: Int,
    currentDate: Int,
    otherName: String,
    userid: String,
) {
    viewModel.getFramesListMonthlyHere(directid, viewMonth)
    val framesHere: List<DirectFrameDataClass> by viewModel.framesListMonthly.observeAsState(
        listOf(
            DirectFrameDataClass(
                id = 0,
                published_date = "",
                frame_picture = "",
                start_time = "",
                end_time = "",
                channel_id = "",
                users = "",
                frame_picture_link = "",
                frame_status = false
            )
        )
    )
    AMonthDirectX(
        directid = directid,
        viewMonth = viewMonth,
        currentMonth = currentMonth,
        currentDate = currentDate,
        framesList = framesHere,
        otherName = otherName,
        userid = userid
    )
}

@Composable
fun AMonthDirectX(
    directid: String,
    viewMonth: Int,
    currentMonth: Int,
    currentDate: Int,
    framesList: List<DirectFrameDataClass>,
    otherName: String,
    userid: String,
) {

    if (viewMonth == currentMonth) {
        AyeTheme() {
            if (currentDate <= 10) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                ) {
                    AStripDirect(
                        start = 1,
                        end = currentDate,
                        framesList = framesList,
                        directid = directid,
                        otherName = otherName,
                        userid = userid
                    )
                }
            } else if (currentDate in 11..20) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                ) {
                    AStripDirect(start = 1, end = 10, framesList = framesList, directid = directid,
                        otherName = otherName,
                        userid = userid)
                    AStripDirect(start = 11, end = currentDate, framesList = framesList, directid = directid,
                        otherName = otherName,
                        userid = userid)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                ) {
                    AStripDirect(start = 1, end = 10, framesList = framesList, directid = directid,
                        otherName = otherName,
                        userid = userid)
                    AStripDirect(start = 11, end = 20, framesList = framesList, directid = directid,
                        otherName = otherName,
                        userid = userid)
                    AStripDirect(start = 21, end = currentDate, framesList = framesList, directid = directid,
                        otherName = otherName,
                        userid = userid)
                }
            }
        }
    } else {
        AyeTheme() {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ) {
                AStripDirect(start = 1, end = 10, framesList = framesList, directid = directid,
                    otherName = otherName,
                    userid = userid)
                AStripDirect(start = 11, end = 20, framesList = framesList, directid = directid,
                    otherName = otherName,
                    userid = userid)
                AStripDirect(start = 21, end = 31, framesList = framesList, directid = directid,
                    otherName = otherName,
                    userid = userid)
            }
        }
    }

}