package com.example.toastgoand.auth.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toastgoand.R
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.databinding.LoginBinding
import com.example.toastgoand.navigator.Navigator
import javax.inject.Inject

class WelcomeActivity : LoginActivity() {

    private lateinit var binding: LoginBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}