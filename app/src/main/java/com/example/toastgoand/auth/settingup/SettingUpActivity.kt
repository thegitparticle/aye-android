package com.example.toastgoand.auth.settingup

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.databinding.ActivitySettingUpBinding
import android.content.pm.PackageManager
import com.example.toastgoand.auth.LoginActivity_MembersInjector.create
import java.util.*
import kotlin.collections.ArrayList
import android.os.AsyncTask

import android.content.ContentResolver
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.alexstyl.contactstore.ContactStore
import com.deepakkumardk.kontactpickerlib.model.MyContacts
import com.example.toastgoand.auth.enterphone.EnterPhoneViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.onComplete


class SettingUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingUpBinding

    private lateinit var viewModel: SettingUpViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingUpViewModel::class.java)

        binding = viewBinding as ActivitySettingUpBinding

        fun filterContactsFromMap(contactMap: MutableMap<String, MyContacts>): MutableList<MyContacts> {
            val myKontacts: MutableList<MyContacts> = arrayListOf()
            val phoneList = arrayListOf<String>()
            contactMap.entries.forEach {
                val contact = it.value

                contact.contactNumberList.forEach { number ->
                    if (!phoneList.contains(number)) {
                        val newContact = MyContacts(
                            contact.contactId,
                            contact.contactName,
                            number, false, "".toUri(),
                            contact.contactNumberList
                        )
                        myKontacts.add(newContact)
                        phoneList.add(number)
                    }
                }
            }
            myKontacts.sortBy {
                it.contactName
            }
            return myKontacts
        }

        fun getAllContacts(activity: Activity?, onCompleted: (MutableList<MyContacts>) -> Unit) {
            val startTime = System.currentTimeMillis()
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )

            val contactMap = mutableMapOf<String, MyContacts>()
            val cr = activity?.contentResolver
            doAsyncResult {
                cr?.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
                    null, null, null
                )?.use {
                    val idIndex = it.getColumnIndex(ContactsContract.Data.CONTACT_ID)
                    val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                    var id: String
                    var name: String
                    var number: String
                    while (it.moveToNext()) {
                        val contacts = MyContacts()
                        id = it.getLong(idIndex).toString()
                        name = it.getString(nameIndex)
                        number = it.getString(numberIndex).replace(" ", "")

                        contacts.contactId = id
                        contacts.contactName = name
                        contacts.contactNumber = number
                        contacts.contactNumberList = arrayListOf(number)

                        if (contactMap[id] != null) {
                            val list = contactMap[id]?.contactNumberList!!
                            if (!list.contains(number))
                                list.add(number)
                            contacts.contactNumberList = list
                        } else {
                            contactMap[id] = contacts
                        }
                    }
                    it.close()
                }
                onComplete {
                    val fetchingTime = System.currentTimeMillis() - startTime
                    Log.i("Fetching Completed", "in $fetchingTime ms")
                    onCompleted.invoke(filterContactsFromMap(contactMap))
                }
                return@doAsyncResult
            }
        }

        viewModel.uploaded.observe(this, {response ->
            if (response == true) {
                val intent = Intent(this, InvitedByActivity::class.java).apply {
                    putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                    putExtra("invitedCheckDataClub", intent.getStringExtra("invitedCheckDataClub"))
                    putExtra("invitedCheckDataUser", intent.getStringExtra("invitedCheckDataUser"))
                    putExtra("userid", intent.getStringExtra("userid"))
                }
                startActivity(intent)
                overridePendingTransition(
                    R.anim.slide_from_right,
                    R.anim.slide_out_left
                )
            } else {
                var toast =
                    Toast.makeText(this, "uploaded is not tru yet", Toast.LENGTH_LONG)
                toast.show()
            }
        })

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Log.i("settingupdebug", "Granted")
                    intent.getStringExtra("phoneNumber")?.let { Log.i("settingupdebug phone", it) }
                    intent.getStringExtra("countryIndicator")?.let { Log.i("settingupdebug country code", it) }

                    var listHere: MutableList<MyContacts>

                    Log.i("settingupdebug log in activity", "logging works")

                    getAllContacts(this) {contactList ->
                        listHere = contactList.toMutableList()

                        val listHereMaps: MutableList<Map<String, String>> = mutableListOf()
                        for (item in listHere) {
                            mapOf("name" to item.contactName.toString(), "phone" to item.contactNumber.toString())?.let {
                                listHereMaps.add(
                                    it
                                )
                            }
                        }

                        Log.i("settingupdebug log in activity", listHereMaps.toString())

                        intent.getStringExtra("countryIndicator")?.let {
                            viewModel.uploadUserContacts(
                                userid = intent.getStringExtra("userid")!!,
                                countryIndicator = it,
                                list = listHereMaps
                            )
                        }
                    }

                    binding.changingText.text = "setting up your profile ..."
                    binding.allowContactsButton.visibility = View.INVISIBLE
                } else {
                    Log.i("settingupdebug", "Denied")
                    binding.changingText.text = "Aye! needs contacts to work"
                    binding.allowContactsButton.visibility = View.VISIBLE

                }
            }

        requestPermissionLauncher.launch(
            Manifest.permission.READ_CONTACTS
        )

        binding.allowContactsButton.setOnClickListener {

            requestPermissionLauncher.launch(
                Manifest.permission.READ_CONTACTS
            )
        }

    }

    override fun binding(): ViewBinding {
        return ActivitySettingUpBinding.inflate(layoutInflater)
    }

}