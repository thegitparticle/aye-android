package com.example.toastgoand.home.clanframes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pubnub.api.PubNub
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import kotlinx.coroutines.launch

class ViewOldFrameClanViewModel: ViewModel() {

    private var _oldMessages = MutableLiveData<List<PNHistoryItemResult>>()

    val oldMessages: LiveData<List<PNHistoryItemResult>>
        get() = _oldMessages

    fun getOldMessages(pubNub: PubNub, channelid: String, start: Long, end: Long) {
        viewModelScope.launch {
            pubNub.history(
                channel = channelid,
                reverse = true,
                includeMeta = true,
                start = start,
                end = end
            ).async { result, status ->
                if (status.error) {
                    Log.i("pubnub - get history clans fail", status.statusCode.toString())
                } else {
                    _oldMessages.value = result?.messages
                    Log.i("pubnub members", _oldMessages.toString())
                }
            }
        }
    }
}