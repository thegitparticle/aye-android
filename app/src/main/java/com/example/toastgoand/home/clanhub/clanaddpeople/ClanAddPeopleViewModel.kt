package com.example.toastgoand.home.clanhub.clanaddpeople

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.myfriends.MyFriendsApi
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.myfriends.MyFriendsRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class ClanAddPeopleViewModel(private val repoDeets: UserDetailsRepo, private val repositoryMyFriends: MyFriendsRepo): ViewModel() {
    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    val myfriends: LiveData<List<MyFriendsDataClass>> = repositoryMyFriends.myFriends.asLiveData()

    private fun insertMyFriendsList(myFriends: List<MyFriendsDataClass>) = viewModelScope.launch {
        repositoryMyFriends.insert(myFriends = myFriends)
    }

    init {
        deets.observeForever {
            deets.value?.user?.id?.let { getMyFriendsListHere(it) }
        }
    }

    fun getMyFriendsListHere(userid: Int) {
        viewModelScope.launch {
            try {
                val myFriendsResult = MyFriendsApi.retrofitService.getMyFriends(userid = userid.toString())
                var y_here: List<MyFriendsDataClass> = myFriendsResult
                insertMyFriendsList(y_here)
                Log.i("ClanAddPeopleViewModel", y_here.toString())
            } catch (e: Exception) {
                Log.i("ClanAddPeopleViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class ClanAddPeopleViewModelFactory(private val repoDeets: UserDetailsRepo, private val repositoryMyFriends: MyFriendsRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClanAddPeopleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClanAddPeopleViewModel(repoDeets, repositoryMyFriends) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}