package com.example.toastgoand.auth.clancreate

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toastgoand.R

class ContactAdapter(private val contactList: List<ContactDataModel>): RecyclerView.Adapter<CreateClanActivity.ContactItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateClanActivity.ContactItemViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item_view, parent, false)

        return CreateClanActivity.ContactItemViewHolder(view as TextView)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: CreateClanActivity.ContactItemViewHolder, position: Int) {

        val ItemsViewModel = contactList[position]

        holder.textView.text = ItemsViewModel.name.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return contactList.size
    }

}