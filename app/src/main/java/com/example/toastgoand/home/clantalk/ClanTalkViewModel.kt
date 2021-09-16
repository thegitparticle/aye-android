package com.example.toastgoand.home.clantalk

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.toastgoand.home.aye.TheAyeViewModel
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import java.lang.IllegalArgumentException

class ClanTalkViewModel(private val repoDeets: UserDetailsRepo): ViewModel() {
    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()
}

class ClanTalkViewModelFactory (private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClanTalkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClanTalkViewModel(repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}