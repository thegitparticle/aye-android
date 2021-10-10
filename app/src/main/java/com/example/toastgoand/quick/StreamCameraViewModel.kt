package com.example.toastgoand.quick

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.quick.network.apis.AgoraTokenApi
import com.example.toastgoand.quick.network.apis.StartClanInfoToServerApi
import com.example.toastgoand.quick.network.apis.StartRecordingApi
import com.example.toastgoand.quick.network.apis.StopClanInfoToServerApi
import kotlinx.coroutines.launch

class StreamCameraViewModel : ViewModel() {

    private var _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token

    fun getTokenHere(userid: Int, channelid: String) {
        viewModelScope.launch {
            try {
                val grabedToken = AgoraTokenApi.retrofitService.getTheToken(
                    userid = userid.toString(),
                    channelid = channelid
                )
                _token.value = grabedToken
                Log.i("streamworking", grabedToken.toString())
            } catch (e: Exception) {
                Log.i("streamworking", "API call for getting tokens, Failed! ${e.message}")
            }
        }

    }

    fun startStreamClubServerCalls(userid: Int, channelid: String, clubid: Int) {
        viewModelScope.launch {
            try {
                val grabedResources = StartRecordingApi.retrofitService.getTheToken(
                    userid = userid.toString(),
                    channelid = channelid
                )
                StartClanInfoToServerApi.retrofitService.startClanStream(
                    userid = userid.toString(),
                    clubid = clubid.toString()
                )
            } catch (e: Exception) {
                Log.i("streamworking", "API call for server call setup, Failed! ${e.message}")
            }
        }
    }

    fun stopStreamClubServerCalls(userid: Int, channelid: String, clubid: Int) {
        viewModelScope.launch {
            try {
                StopClanInfoToServerApi.retrofitService.stopClanStream(
                    userid = userid.toString(),
                    clubid = clubid.toString()
                )
            } catch (e: Exception) {
                Log.i("streamworking", "API call for stop stream, Failed! ${e.message}")
            }
        }
    }

}