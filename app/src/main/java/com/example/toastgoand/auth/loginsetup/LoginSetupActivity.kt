package com.example.toastgoand.auth.loginsetup

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.alexstyl.contactstore.ContactStore
import com.deepakkumardk.kontactpickerlib.KontactPicker
import com.deepakkumardk.kontactpickerlib.model.KontactPickerItem
import com.deepakkumardk.kontactpickerlib.model.MyContacts
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.databinding.ActivityLoginSetupBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.InternalCoroutinesApi

class LoginSetupActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginSetupBinding

    private val viewModel: LoginSetupViewModel by viewModels {
        LoginSetupViewModelFactory((application as ToastgoApplication).repository)
    }

    lateinit var prefHelper: PrefHelper

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KontactPicker().startPickerForResult(this, KontactPickerItem(), 3000)

        binding = viewBinding as ActivityLoginSetupBinding

        val store = ContactStore.newInstance(application)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Log.i("settingupdebug", "Granted")
                    intent.getStringExtra("phoneNumber")?.let { Log.i("settingupdebug phone", it) }
                    intent.getStringExtra("countryIndicator")?.let { Log.i("settingupdebug country code", it) }

                    var listHere: MutableList<Any>

                    KontactPicker.getAllKontacts(this) {contactList ->
                        listHere = contactList.toMutableList()
                        intent.getStringExtra("countryIndicator")?.let {
                            viewModel.uploadUserContacts(
                                userid = intent.getStringExtra("userid")!!,
                                countryIndicator = it,
                                list = listHere
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

        intent.getStringExtra("phoneNumber")?.let { viewModel.getUserDetailsWhileLoginHere(it) }

        viewModel.uploaded.observe(this, Observer { response ->
            if (response) {
                prefHelper = PrefHelper(this)
                prefHelper.put(Constant.PREF_IS_LOGIN, true)
                val intent = Intent(this, LandingActivity::class.java).apply {
                    putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                }
                startActivity(intent)
            }
            Log.i("observer", "obseving is happening")
        })

    }

    override fun binding(): ViewBinding {
        return ActivityLoginSetupBinding.inflate(layoutInflater)
    }
}