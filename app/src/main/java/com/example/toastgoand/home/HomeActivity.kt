package com.example.toastgoand.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityHomeBinding
import com.example.toastgoand.databinding.LoginBinding
import com.example.toastgoand.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity: BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    lateinit var navController: NavController

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityHomeBinding

        window.statusBarColor = Color.TRANSPARENT

        initViews()
    }

    override fun binding(): ViewBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }


    private fun initViews(){

        setUpBottomNavigation()

    }

    private fun setUpBottomNavigation(){

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemReselectedListener {
            //do something when selected twice
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(
                item,
                Navigation.findNavController(this, R.id.nav_host_fragment)
            )
        }

        binding.bottomNavigation.itemIconTintList = null
//
////        //if we are viewing stories, hide the bottom navigation
//        navController.addOnDestinationChangedListener { _, destination, _ ->
////            if(true) {
//
//                binding.bottomNavigation.visibility = View.VISIBLE
////            } else {
////                binding.bottomNavigation.visibility = View.GONE
////            }
//        }
    }
}