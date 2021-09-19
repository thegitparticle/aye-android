package com.example.toastgoand.uibits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import androidx.viewbinding.ViewBinding
import app.futured.hauler.setOnDragDismissedListener
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityTheAyeBinding
import com.example.toastgoand.databinding.ActivityViewMediaBinding
import kotlinx.android.synthetic.main.activity_view_media.*

class ViewMediaActivity : BaseActivity() {
    private lateinit var binding: ActivityViewMediaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityViewMediaBinding

        val imageLink = intent.getStringExtra("imagelink")

        binding.imageView2.setImageURI(imageLink?.toUri())

        haulerView.setOnDragDismissedListener {
            finish() // finish activity when dismissed
        }
    }

    override fun binding(): ViewBinding {
        return ActivityViewMediaBinding.inflate(layoutInflater)
    }
}