package com.example.toastgoand.home.aye

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo

class TheAyeViewModel(private val repoDeets: UserDetailsRepo): ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

}

class TheAyeViewModelFactory (private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TheAyeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TheAyeViewModel(repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}