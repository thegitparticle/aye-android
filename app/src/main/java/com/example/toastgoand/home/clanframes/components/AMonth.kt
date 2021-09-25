package com.example.toastgoand.home.clanframes.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanframes.ClanFrameDataClass
import com.example.toastgoand.home.clanframes.network.ClanFramesListApi
import kotlinx.coroutines.launch

class AMonthClanViewModel : ViewModel() {

    private val _framesListMonthly = MutableLiveData<List<ClanFrameDataClass>>()
    val framesListMonthly: LiveData<List<ClanFrameDataClass>>
        get() = _framesListMonthly

    fun getFramesListMonthlyHere(clubid: String, viewMonth: Int) {
        viewModelScope.launch {
            try {
                var framesListHere = ClanFramesListApi.retrofitService.getMonthlyFramesList(
                    viewMonth.toString(),
                    clubid
                )
                _framesListMonthly.value = framesListHere
                Log.i("frameslistViewModelClan", framesListHere.toString())
            } catch (e: Exception) {
                Log.i("frameslistViewModelClan", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

@Composable
fun AMonth(
    viewModel: AMonthClanViewModel,
    clubid: String,
    viewMonth: Int,
    currentMonth: Int,
    currentDate: Int,
    clubName: String,
    userid: String, channelid: String
) {
    viewModel.getFramesListMonthlyHere(clubid, viewMonth)
    val framesHere: List<ClanFrameDataClass> by viewModel.framesListMonthly.observeAsState(
        listOf(
            ClanFrameDataClass(
                id = 0,
                published_date = "",
                frame_picture = "",
                start_time = "",
                end_time = "",
                channel_id = "",
                club_name = 0,
                frame_picture_link = "",
                frame_status = false
            )
        )
    )

    AMonthX(
        channelid = channelid,
        viewMonth = viewMonth,
        currentMonth = currentMonth,
        currentDate = currentDate,
        framesList = framesHere,
        clubName = clubName, userid = userid
    )
}


@Composable
fun AMonthX(
    channelid: String,
    viewMonth: Int,
    currentMonth: Int,
    currentDate: Int,
    framesList: List<ClanFrameDataClass>,
    clubName: String,
    userid: String
) {

    if (viewMonth == currentMonth) {
        AyeTheme() {
            if (currentDate <= 10) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().background(AyeTheme.colors.uiBackground)
                ) {
                    AStrip(start = 1, end = currentDate, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                }
            } else if (currentDate in 11..20) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().background(AyeTheme.colors.uiBackground)
                ) {
                    AStrip(start = 1, end = 10, framesList = framesList, clubName, userid, channelid = channelid)
                    AStrip(start = 11, end = currentDate, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().background(AyeTheme.colors.uiBackground)
                ) {
                    AStrip(start = 1, end = 10, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                    AStrip(start = 11, end = 20, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                    AStrip(start = 21, end = currentDate, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                }
            }
        }
    } else {
        AyeTheme() {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().background(AyeTheme.colors.uiBackground)
            ) {
                AStrip(start = 1, end = 10, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                AStrip(start = 11, end = 20, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
                AStrip(start = 21, end = 31, framesList = framesList, clubName = clubName, userid = userid, channelid = channelid)
            }
        }
    }

}