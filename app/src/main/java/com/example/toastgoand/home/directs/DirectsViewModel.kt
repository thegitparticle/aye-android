package com.example.toastgoand.home.directs

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.directs.MyDirectsApi
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.directs.MyDirectsRepo
import com.example.toastgoand.network.nudgelist.NudgeToApi
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.network.nudgelist.NudgeToRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DirectsViewModel(private val repo: MyDirectsRepo, private val repoDeets: UserDetailsRepo, private val repoNudgeTo: NudgeToRepo) : ViewModel() {

    val myDirects: LiveData<List<MyDirectsDataClass>> = repo.myDirects.asLiveData()
    val nudgeTo: LiveData<List<NudgeToDataClass>> = repoNudgeTo.nudgeTo.asLiveData()
    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            _isRefreshing.emit(true)
            deets.value?.user?.let { getMyDirectsHere(it.id); getNudgeToHere(it.id) }
            delay(2000)
            _isRefreshing.emit(false)
        }
    }

    private fun insertDirects(myDirects: List<MyDirectsDataClass>) = viewModelScope.launch {
        repo.insert(myDirects)
    }

    private fun insertNudgeTo(nudgeTo: List<NudgeToDataClass>) = viewModelScope.launch {
        repoNudgeTo.insert(nudgeTo)
    }

    init {
        deets.observeForever {
            deets.value?.user?.let { getMyDirectsHere(it.id); getNudgeToHere(it.id) }
        }
    }

    fun getMyDirectsHere(userid: Int) {
        viewModelScope.launch {
            try {
                val myDirectsResult = MyDirectsApi.retrofitService.getMyDirects(userid.toString())
                var x_here: List<MyDirectsDataClass> = myDirectsResult
                insertDirects(x_here)
                Log.i("directdebug", x_here.toString())
            } catch (e: Exception) {
                Log.i("directdebug", "API call for user details, Failed! ${e.message}")
            }
        }
    }

    fun getNudgeToHere(userid: Int) {
        viewModelScope.launch {
            try {
                val nudgeToResult = NudgeToApi.retrofitService.getNudgeTo(userid.toString())
                var y_here: List<NudgeToDataClass> = nudgeToResult
                insertNudgeTo(y_here)
                Log.i("DirectsViewModelNudge", y_here.toString())
            } catch (e: Exception) {
                Log.i("DirectsViewModelNudge", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class DirectsViewModelFactory (private val repo: MyDirectsRepo, private val repoDeets: UserDetailsRepo, private val repoNudgeTo: NudgeToRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DirectsViewModel(repo, repoDeets, repoNudgeTo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}