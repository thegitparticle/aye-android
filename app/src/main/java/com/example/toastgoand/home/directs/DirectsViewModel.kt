package com.example.toastgoand.home.directs

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.directs.MyDirectsApi
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.directs.MyDirectsRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class DirectsViewModel(private val repo: MyDirectsRepo, private val repoDeets: UserDetailsRepo) : ViewModel() {

    val myDirects: LiveData<List<MyDirectsDataClass>> = repo.myDirects.asLiveData()
    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    private fun insert(myDirects: List<MyDirectsDataClass>) = viewModelScope.launch {
        repo.insert(myDirects)
    }

    init {
        deets.observeForever {
            deets.value?.user?.let { getMyDirectsHere(it.id) }
        }
    }

    fun getMyDirectsHere(userid: Int) {
        viewModelScope.launch {
            try {
                val myDirectsResult = MyDirectsApi.retrofitService.getMyDirects(userid.toString())
                var x_here: List<MyDirectsDataClass> = myDirectsResult
                insert(x_here)
//                repo.insert(x_here)
                Log.i("DirectsViewModel", x_here.toString())
            } catch (e: Exception) {
                Log.i("DirectsViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class DirectsViewModelFactory (private val repo: MyDirectsRepo, private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DirectsViewModel(repo, repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}