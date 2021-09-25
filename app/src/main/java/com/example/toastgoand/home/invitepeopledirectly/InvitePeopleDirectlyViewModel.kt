package com.example.toastgoand.home.invitepeopledirectly

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.myfriends.MyFriendsRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo

class InvitePeopleDirectlyViewModel(
    private val repoDeets: UserDetailsRepo,
    private val repositoryMyFriends: MyFriendsRepo
) : ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    val myfriends: LiveData<List<MyFriendsDataClass>> = repositoryMyFriends.myFriends.asLiveData()
}

class InvitePeopleDirectlyViewModelFactory(private val repoDeets: UserDetailsRepo, private val repositoryMyFriends: MyFriendsRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InvitePeopleDirectlyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InvitePeopleDirectlyViewModel(repoDeets, repositoryMyFriends) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}