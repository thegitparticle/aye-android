package com.example.toastgoand.home

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityLandingBinding
import com.example.toastgoand.databinding.StartCallDialogBinding
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LandingActivity: BaseActivity() {

    private lateinit var binding: ActivityLandingBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityLandingBinding

        // start stream button login

        binding.floatingActionButton.setOnClickListener{
            Toast.makeText(this, "start stream", Toast.LENGTH_SHORT).show()
            showDefaultDialog()
            binding.navView.visibility = View.GONE
        }

        // below is all code for bottom navigation
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