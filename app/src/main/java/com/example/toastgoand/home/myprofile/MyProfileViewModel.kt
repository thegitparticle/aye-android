package com.example.toastgoand.home.myprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo

class MyProfileViewModel(private val repoDeets: UserDetailsRepo): ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

}

class MyProfileViewModelFactory (private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyProfileViewModel(repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}