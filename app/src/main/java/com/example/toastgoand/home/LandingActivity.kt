package com.example.toastgoand.home

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityLandingBinding
import com.example.toastgoand.databinding.LoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LandingActivity: BaseActivity() {

    private lateinit var binding: ActivityLandingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.statusBarColor = Color.TRANSPARENT

        binding = viewBinding as ActivityLandingBinding

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_landing)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun binding(): ViewBinding {
        return ActivityLandingBinding.inflate(layoutInflater)
    }
}