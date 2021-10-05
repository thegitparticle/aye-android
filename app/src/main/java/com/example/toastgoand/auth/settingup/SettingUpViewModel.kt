package com.example.toastgoand.auth.settingup

import android.util.Log
import androidx.lifecycle.*
import com.alexstyl.contactstore.ContactStore
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.network.apis.UploadContactsApi
import com.example.toastgoand.auth.network.dataclasses.CountryCodeDataClass
import com.example.toastgoand.auth.network.dataclasses.UploadContactsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SettingUpViewModel: ViewModel()  {

//    private val context = getApplication<ToastgoApplication>().applicationContext

    private val _uploaded = MutableLiveData<Boolean>(false)
    val uploaded: LiveData<Boolean>
        get() = _uploaded

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
    }

    @InternalCoroutinesApi
    fun uploadUserContacts(userid: String, countryIndicator: String, list: MutableList<Map<String, String>>) {
        Log.i("settingupdebug", "upload contacts function in vm called")
        viewModelScope.launch {
            try {
                var payload: UploadContactsDataClass = UploadContactsDataClass(contact_list = "")
                var countryCode = mapOf("country_code" to countryIndicator)

                list.add(0, countryCode)
                payload.contact_list = Json.encodeToString(list)
                val contactsSuccess = UploadContactsApi.retrofitService.uploadContacts(userid = userid, data = payload)
                _uploaded.value = true

            } catch (e: Exception) {
                _uploaded.value = false
                Log.i("settingupdebug", "upload contacts PUT call for user details, Failed! ${e.message}")
            }
        }
    }

}
