package com.example.toastgoand

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity: AppCompatActivity() {
    lateinit var viewBinding: ViewBinding
    abstract fun binding(): ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = binding()
        setContentView(viewBinding.root)
    }

    open fun ShowMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}