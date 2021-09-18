package com.example.toastgoand.home

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import androidx.viewpager.widget.ViewPager
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.databinding.ActivityLandingBinding
import com.example.toastgoand.databinding.StartCallDialogBinding
import com.example.toastgoand.home.aye.TheAyeActivity
import com.example.toastgoand.home.clans.ClansFragment
import com.example.toastgoand.home.directs.DirectsFragment
import com.example.toastgoand.home.invitepeopledirectly.InvitePeopleDirectlyActivity
import com.example.toastgoand.home.myprofile.MyProfileActivity
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.tabs.TabLayout


@AndroidEntryPoint
class LandingActivity: BaseActivity() {

    private lateinit var binding: ActivityLandingBinding

    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout

    @Inject
    lateinit var navigator: Navigator

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityLandingBinding

//        // start stream button login
//        binding.floatingActionButton.setOnClickListener{
//            Toast.makeText(this, "start stream", Toast.LENGTH_SHORT).show()
//            showDefaultDialog()
//            binding.navView.visibility = View.GONE
//        }

        binding.logout.setOnClickListener {
            prefHelper = PrefHelper(this)
            prefHelper.put( Constant.PREF_IS_LOGIN, false)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.circleImage.setOnClickListener {
            val intent = Intent(this, MyProfileActivity::class.java).apply{}
            startActivity(intent)
            overridePendingTransition(R.anim.slide_up_enter,
                R.anim.slide_down_exit)
        }

        binding.ayeLogo.setOnClickListener {
            val intent = Intent(this, TheAyeActivity::class.java).apply{}
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left,
                R.anim.slide_out_left)
        }

        binding.invitePeopleLogo.setOnClickListener {
            val intent = Intent(this, InvitePeopleDirectlyActivity::class.java).apply{}
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left,
                R.anim.slide_out_left)
        }

        pager = binding.viewPager
        tab = binding.navView

        val adapter = HomePagerAdapter(supportFragmentManager)

        adapter.addFragment(ClansFragment(), "CLANS")
        adapter.addFragment(DirectsFragment(), "DIRECTS")

        pager.adapter = adapter

        tab.setupWithViewPager(pager)


//        // below is all code for bottom navigation
//        val navView: TabLayout = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_landing)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard,
//            )
//        )
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        getSupportActionBar()?.hide()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Firebase Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            Log.d(ContentValues.TAG, "Firebase token: " + token)
//
            prefHelper = PrefHelper(this)
            if (token != null) {
                prefHelper.put( Constant.FIREBASE_TOKEN , token)
            }

        })

    }

    override fun onResume() {
        super.onResume()
        binding.navView.visibility = View.VISIBLE
    }

    private fun showDefaultDialog() {
        val dialogBinding: StartCallDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.start_call_dialog,
                null,
                false
            )

        val customDialog = AlertDialog.Builder(this, 0).create()

        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.btnOk?.setOnClickListener {
            customDialog.dismiss()
            navigator.navigateTo(Screen.QUICK)
        }

        dialogBinding?.backImageButton?.setOnClickListener{
            customDialog.dismiss()
            binding.navView.visibility = View.VISIBLE
        }
    }

    override fun binding(): ViewBinding {
        return ActivityLandingBinding.inflate(layoutInflater)
    }
}