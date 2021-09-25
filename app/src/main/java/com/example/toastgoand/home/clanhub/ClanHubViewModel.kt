package com.example.toastgoand.home.clanhub

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.home.clanhub.network.ClanDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class ClanHubViewModel(private val repoDeets: UserDetailsRepo): ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    private val _clubDetails = MutableLiveData<ClanDetailsDataClass>()
    val clubDetails: LiveData<ClanDetailsDataClass>
        get() = _clubDetails


    fun getClanDetailsHere(clubid: Int) {
        viewModelScope.launch {
            try {
                val userResult = ClanDetailsApi.retrofitService.getUserDetails(clubid.toString())
                var x_here: ClanDetailsDataClass = userResult
                _clubDetails.value = x_here
                Log.i("clanHubViewModel", _clubDetails.value!!.toString())
            } catch (e: Exception) {
                Log.i("clanHubViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }

}