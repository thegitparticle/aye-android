package com.example.toastgoand.auth.clancreate

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.databinding.ActivityCreateClanBinding
import java.util.*

class CreateClanActivity : BaseActivity() {
    private lateinit var binding: ActivityCreateClanBinding

    private lateinit var viewModel: CreateClanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityCreateClanBinding

        viewModel = ViewModelProvider(this).get(CreateClanViewModel::class.java)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.contactsList)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ContactDataModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ContactDataModel(i, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = ContactAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        binding.floatingNextButton.setOnClickListener {
            val intent = Intent(this, NameClanActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityCreateClanBinding.inflate(layoutInflater)
    }

    class ContactItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
}