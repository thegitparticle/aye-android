package com.example.toastgoand.home.clantalk

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.defaultrecos.DefaultRecosApi
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.network.defaultrecos.DefaultRecosRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClanTalkViewModel(repoDeets: UserDetailsRepo, private val repoRecos: DefaultRecosRepo) :
    ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()
    val recos: LiveData<List<DefaultRecosDataClass>> = repoRecos.defaultRecos.asLiveData()

    private var _oldMessages = MutableLiveData<List<PNHistoryItemResult>>()
    val oldMessages: LiveData<List<PNHistoryItemResult>>
        get() = _oldMessages

    private var _newMessages = MutableLiveData<List<PNMessageResult>>()
    val newMessages: LiveData<List<PNMessageResult>>
        get() = _newMessages

    private fun insertDefaultRecos(recos: List<DefaultRecosDataClass>) = viewModelScope.launch {
        repoRecos.insert(recos)
    }

    init {
        deets.observeForever {
            deets.value?.user?.let { getDefaultRecosHere(it.id) }
            Log.i("livemessage", deets.value?.user?.id.toString())
        }

        _newMessages.observeForever{
            Log.i("livemessage observe in vm", _newMessages.toString())
        }
    }

    fun watchLiveMessages(pubnub: PubNub, channelid: String, start: Long, end: Long) {
        viewModelScope.launch {
            pubnub.addListener(object : SubscribeCallback() {
                override fun status(pubnub: PubNub, status: PNStatus) {
                    Log.i("livemessage", "Status category: ${status.category}")
                    // PNConnectedCategory, PNReconnectedCategory, PNDisconnectedCategory
                    Log.i("livemessage", "Status operation: ${status.operation}")
                    // PNSubscribeOperation, PNHeartbeatOperation

                    Log.i("livemessage", "Status error: ${status.error}")
                    // true or false
                }

                override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
                    Log.i("livemessage", "Message payload: ${pnMessageResult}")
                    _newMessages.postValue(listOf(pnMessageResult))
//                    _newMessages.value?.add(pnMessageResult)
//                    _newMessages.postValue(_newMessages.value?.plus(listOf(pnMessageResult)))
//                    GlobalScope.launch {
//                        withContext(Dispatchers.Main) {_newMessages.value = _newMessages.value?.plus(pnMessageResult)}
//                    }
//                    _newMessages.value?.get(0)
//                        ?.let { Log.i("livemessage - inside message listener", it.toString()) }
                    Log.i("livemessage", "Message channel: ${pnMessageResult.channel}")
                    Log.i("livemessage","Message publisher: ${pnMessageResult.publisher}")
                    Log.i("livemessage","Message timetoken: ${pnMessageResult.timetoken}")
                }
            })
        }
    }

    fun getDefaultRecosHere(userid: Int) {
        viewModelScope.launch {
            try {
                val defaultRecosResult =
//                    DefaultRecosApi.retrofitService.getDefaultRecos(userid.toString())
                DefaultRecosApi.retrofitService.getDefaultRecos()
                var x_here: List<DefaultRecosDataClass> = defaultRecosResult
                insertDefaultRecos(x_here)
                Log.i("defaultrecos", x_here.toString())
            } catch (e: Exception) {
                Log.i("defaultrecos", "API call for default recos, Failed! ${e.message}")
            }
        }

    }

    fun getOldMessages(pubNub: PubNub, channelid: String ,start: Long, end: Long) {
        viewModelScope.launch {
            pubNub.history(
                channel = channelid,
                reverse = false,
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

class ClanTalkViewModelFactory(
    private val repoDeets: UserDetailsRepo,
    private val repoRecos: DefaultRecosRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClanTalkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClanTalkViewModel(repoDeets, repoRecos) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}