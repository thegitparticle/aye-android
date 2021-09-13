package com.example.toastgoand.home.startclan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import java.lang.IllegalArgumentException

class StartClanViewModel(private val repoDeets: UserDetailsRepo): ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()
}

class StartClanViewModelFactory (private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartClanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StartClanViewModel(repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}