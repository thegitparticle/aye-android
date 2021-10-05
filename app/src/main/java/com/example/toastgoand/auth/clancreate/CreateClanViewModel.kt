package com.example.toastgoand.auth.clancreate

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.home.invitepeopledirectly.network.ContactsListItemDataClass
import com.example.toastgoand.home.invitepeopledirectly.network.GetContactsListApi
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.myfriends.MyFriendsRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class CreateClanViewModel(
    private val repoDeets: UserDetailsRepo,
    private val repositoryMyFriends: MyFriendsRepo
) : ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    val myfriends: LiveData<List<MyFriendsDataClass>> = repositoryMyFriends.myFriends.asLiveData()

    private val _contactsList = MutableLiveData<List<ContactsListItemDataClass>>()
    val contactsList: LiveData<List<ContactsListItemDataClass>>
        get() = _contactsList

    init {
        deets.observeForever {
            deets.value?.user?.let { getContactsListHere(it.id) }
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

class CreateClanViewModelFactory(private val repoDeets: UserDetailsRepo, private val repositoryMyFriends: MyFriendsRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateClanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateClanViewModel(repoDeets, repositoryMyFriends) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}