package com.example.toastgoand.home.clantalk

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.home.aye.TheAyeViewModel
import com.example.toastgoand.network.defaultrecos.DefaultRecosApi
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.network.defaultrecos.DefaultRecosRepo
import com.example.toastgoand.network.directs.MyDirectsApi
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import com.pubnub.api.PubNub
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ClanTalkViewModel(repoDeets: UserDetailsRepo, private val repoRecos: DefaultRecosRepo) :
    ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()
    val recos: LiveData<List<DefaultRecosDataClass>> = repoRecos.defaultRecos.asLiveData()

    private var _oldMessages = MutableLiveData<List<PNHistoryItemResult>>()

    val oldMessages: LiveData<List<PNHistoryItemResult>>
        get() = _oldMessages

    private fun insertDefaultRecos(recos: List<DefaultRecosDataClass>) = viewModelScope.launch {
        repoRecos.insert(recos)
    }

    init {
        deets.observeForever {
            deets.value?.user?.let { getDefaultRecosHere(it.id) }
            Log.i("defaultrecos userid", deets.value?.user?.id.toString())
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