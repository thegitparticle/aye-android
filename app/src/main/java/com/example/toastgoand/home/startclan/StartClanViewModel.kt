package com.example.toastgoand.home.startclan

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.home.invitepeopledirectly.network.ContactsListItemDataClass
import com.example.toastgoand.home.invitepeopledirectly.network.GetContactsListApi
import com.example.toastgoand.network.myfriends.MyFriendsApi
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.myfriends.MyFriendsRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class StartClanViewModel(
    private val repoFriends: MyFriendsRepo,
    private val repoDeets: UserDetailsRepo
) : ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    val friendsList: LiveData<List<MyFriendsDataClass>> = repoFriends.myFriends.asLiveData()

    private fun insert(myFriends: List<MyFriendsDataClass>) = viewModelScope.launch {
        repoFriends.insert(myFriends = myFriends)
    }

    private val _contactsList = MutableLiveData<List<ContactsListItemDataClass>>()
    val contactsList: LiveData<List<ContactsListItemDataClass>>
        get() = _contactsList

    init {
        deets.observeForever {
            deets.value?.user?.let { getMyFriendsHere(it.id) }
            deets.value?.user?.let { getContactsListHere(it.id) }
        }
    }

    fun getMyFriendsHere(userid: Int) {
        viewModelScope.launch {
            try {
                val myFriendsResult = MyFriendsApi.retrofitService.getMyFriends(userid.toString())
                var x_here: List<MyFriendsDataClass> = myFriendsResult
                insert(x_here)
                Log.i("StartClanViewModel", x_here.toString())
            } catch (e: Exception) {
                Log.i("StartClanViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }

    fun getContactsListHere(userid: Int) {
        viewModelScope.launch {
            try {
                val listResult = GetContactsListApi.retrofitService.getMyFriends(userid = userid.toString())
                var x_here: List<ContactsListItemDataClass> = listResult
                _contactsList.value = x_here
                Log.i("contactsViewModel", _contactsList.value!!.toString())
            } catch (e: Exception) {
                Log.i("contactsViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class StartClanViewModelFactory(
    private val repoFriends: MyFriendsRepo,
    private val repoDeets: UserDetailsRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartClanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StartClanViewModel(repoFriends, repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}