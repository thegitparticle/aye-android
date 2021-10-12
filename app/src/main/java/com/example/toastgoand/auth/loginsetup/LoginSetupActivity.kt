package com.example.toastgoand.auth.loginsetup

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.alexstyl.contactstore.ContactStore
import com.deepakkumardk.kontactpickerlib.KontactPicker
import com.deepakkumardk.kontactpickerlib.model.KontactPickerItem
import com.deepakkumardk.kontactpickerlib.model.MyContacts
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.databinding.ActivityLoginSetupBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_create_clan.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.onComplete
import org.kpropmap.propMapOf
import kotlin.reflect.full.memberProperties

class LoginSetupActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginSetupBinding

    private val viewModel: LoginSetupViewModel by viewModels {
        LoginSetupViewModelFactory((application as ToastgoApplication).repository)
    }

    lateinit var prefHelper: PrefHelper

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra("phoneNumber")?.let { viewModel.getUserDetailsWhileLoginHere(it) }

        var listOfContactsDirect: MutableList<MyContacts>


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

        binding = viewBinding as ActivityLoginSetupBinding

        binding.changingText.text = "setting up your profile ..."
        binding.animationView.setAnimation(R.raw.loading_ping_pong_cup)

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
                    binding.animationView.setAnimation(R.raw.loading_ping_pong_cup)
                    binding.allowContactsButton.visibility = View.INVISIBLE
                } else {
                    Log.i("settingupdebug", "Denied")
                    binding.changingText.text = "Aye! needs contacts to work"
                    binding.animationView.setAnimation(R.raw.contacts_lottie)
                    binding.allowContactsButton.visibility = View.VISIBLE

                }
            }

        requestPermissionLauncher.launch(
            Manifest.permission.READ_CONTACTS
        )

        viewModel.uploaded.observe(this, Observer { response ->
            if (response) {
                prefHelper = PrefHelper(this)
                prefHelper.put(Constant.PREF_IS_LOGIN, true)
                Log.i("settingupdebug", "uploaded boolean value is observed to be changed")
                val intent = Intent(this, LandingActivity::class.java).apply {
                    putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                }
                startActivity(intent)
                Log.i("settingupdebug", "uploaded boolean value is observed to be changed after stareting activity")
            }
            Log.i("settingupdebug", "obseving is happening")
        })

    }

    override fun binding(): ViewBinding {
        return ActivityLoginSetupBinding.inflate(layoutInflater)
    }
}