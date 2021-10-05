package com.example.toastgoand.auth.invitedby

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.myfriends.MyFriendsApi
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import kotlinx.coroutines.launch

class InvitedByViewModel: ViewModel() {

    private val _friendsList = MutableLiveData<List<MyFriendsDataClass>>()
    val friendsList: LiveData<List<MyFriendsDataClass>>
        get() = _friendsList

    fun getMyFriendsHere(userid: String) {
        viewModelScope.launch {
            try {
                val myFriendsResult = MyFriendsApi.retrofitService.getMyFriends(userid)
                var x_here: List<MyFriendsDataClass> = myFriendsResult
                _friendsList.value = x_here
                Log.i("StartClanViewModel", x_here.toString())
            } catch (e: Exception) {
                Log.i("StartClanViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}