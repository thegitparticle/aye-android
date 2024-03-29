package com.example.toastgoand.quick

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.quick.network.apis.*
import kotlinx.coroutines.launch

class StreamMultiViewModel: ViewModel() {
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

    fun startStreamDirectServerCalls(userid: Int, channelid: String) {
        viewModelScope.launch {
            try {
                val grabedResources = StartRecordingApi.retrofitService.getTheToken(
                    userid = userid.toString(),
                    channelid = channelid
                )
                StartDirectInfoToServerApi.retrofitService.startDirectStream(
                    userid = userid.toString(),
                    channelid = channelid
                )
            } catch (e: Exception) {
                Log.i("streamworking", "API call for server call setup, Failed! ${e.message}")
            }
        }
    }

    fun stopStreamDirectServerCalls(userid: Int, channelid: String) {
        viewModelScope.launch {
            try {
                StopDirectInfoToServerApi.retrofitService.stopDirectStream(
                    userid = userid.toString(),
                    channelid = channelid
                )
            } catch (e: Exception) {
                Log.i("streamworking", "API call for stop stream, Failed! ${e.message}")
            }
        }
    }
}