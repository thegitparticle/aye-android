package com.example.toastgoand.quick

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.quick.network.apis.AgoraTokenApi
import kotlinx.coroutines.launch

class WatchStreamViewModel: ViewModel() {

    private var _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token

    fun getTokenHere(userid: Int, channelid: String) {
        viewModelScope.launch {
            try {
                val grabedToken = AgoraTokenApi.retrofitService.getTheToken(userid = userid.toString(), channelid = channelid)
                _token.value = grabedToken
                Log.i("streamworking", grabedToken.toString())
            } catch (e: Exception) {
                Log.i("streamworking", "API call for default recos, Failed! ${e.message}")
            }
        }

    }

}