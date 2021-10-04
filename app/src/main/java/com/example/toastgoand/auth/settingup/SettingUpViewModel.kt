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
    fun uploadUserContacts(userid: String, countryIndicator: String, store: ContactStore) {
        viewModelScope.launch {
            try {
                var payload: UploadContactsDataClass = UploadContactsDataClass(contact_list = "")
                var countryCode = CountryCodeDataClass(country_code = countryIndicator)

                store.fetchContacts().collect { contactsList ->
                    Log.i("contactswork", contactsList.toString())
                    var mutable_list: MutableList<Any> = contactsList.toMutableList()
                    mutable_list.add(0, countryCode)
                    payload.contact_list = mutable_list.toString()
                    val contactsSuccess = UploadContactsApi.retrofitService.uploadContacts(userid = userid, data = payload)
                    _uploaded.value = true
                }
//                    .collect { contacts ->
////                        val contactString = contacts.joinToString(", ") {
////                            "DisplayName = ${it.displayName}, isStarred = ${it.isStarred}, id = ${it.contactId}"
////                        }
////                        println("Contacts emitted: $contactString")
//                    }
            } catch (e: Exception) {
                _uploaded.value = false
                Log.i("SettingUpViewModel", "upload contacts PUT call for user details, Failed! ${e.message}")
            }
        }
    }

}
