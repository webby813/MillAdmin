package com.example.milladmin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.milladmin.Database.MillData
import com.example.milladmin.Database.UserData
import com.example.milladmin.Database.UsersDataModel
import com.example.milladmin.R
import com.example.milladmin.home.ManageMill
import com.example.milladmin.home.ViewUsers
import com.example.milladmin.users.ManageUsers

class ItemAdapter(val context: Context, val items: List<MillData>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mill_row, parent, false)
        return ViewHolder(view, context, items) // Pass the items list to ViewHolder
    }

    class ViewHolder(view: View, private val context: Context, private val items: List<MillData>) : RecyclerView.ViewHolder(view) {
        val millName = view.findViewById<TextView>(R.id.mill_name)

        init {
            view.findViewById<ImageButton>(R.id.edit_mill).setOnClickListener {
                val intent = Intent(context, ManageMill::class.java)
                val currentItem = items[adapterPosition]
                intent.putExtra("PiID", currentItem.PiID)
                intent.putExtra("name", currentItem.name)
                intent.putExtra("status", currentItem.status)
                intent.putExtra("steamPress", currentItem.steamPress)
                intent.putExtra("SteamFlow", currentItem.SteamFlow)
                intent.putExtra("WtrLevel", currentItem.WtrLevel)
                intent.putExtra("PwrFrq", currentItem.PwrFrq)
                intent.putExtra("mode", currentItem.mode)
                intent.putExtra("proxyport", currentItem.proxyport)
                intent.putExtra("tunnelport", currentItem.tunnelport)

                context.startActivity(intent)
            }
        }

        init {
            view.findViewById<ImageButton>(R.id.view_users).setOnClickListener {
                val intent = Intent(context, ViewUsers::class.java)
                context.startActivity(intent)
            }
        }

        fun bind(item: MillData) {
            millName.text = item.PiID
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class UsersAdapter(val context: Context, val items: List<UserData>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)

        // Set the visibility of persona_detail based on the activity
        holder.showPersonaDetail(context !is ViewUsers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.user_row,
                parent,
                false
            ),
            context
        )
    }

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view){
        val userName = view.findViewById<TextView>(R.id.user_name)
        val userPhone = view.findViewById<TextView>(R.id.user_phone)
        val usersViewItem = view.findViewById<CardView>(R.id.user_row_items)
        val personaDetail = view.findViewById<ImageButton>(R.id.persona_detail)

        fun showPersonaDetail(isVisible: Boolean) {
            personaDetail.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

        fun bind(item: UserData) {
            userName.text = item.name
            userPhone.text = item.phoneNum.toString()
        }

        init {
            personaDetail.setOnClickListener {
                val intent = Intent(context, ManageUsers::class.java)
                intent.putExtra("userName", userName.text.toString())
                context.startActivity(intent)
            }
        }

    }
    override fun getItemCount(): Int {
        return items.size
    }
}

class MillViewUsers(val context: Context, val items: ArrayList<UsersDataModel>) : RecyclerView.Adapter<MillViewUsers.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)

        // Set the visibility of persona_detail based on the activity
        holder.showPersonaDetail(context !is ViewUsers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.user_row,
                parent,
                false
            ),
            context
        )
    }

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view){
        val userName = view.findViewById<TextView>(R.id.user_name)
        val userPhone = view.findViewById<TextView>(R.id.user_phone)
        val usersViewItem = view.findViewById<CardView>(R.id.user_row_items)
        val personaDetail = view.findViewById<ImageButton>(R.id.persona_detail)

        fun showPersonaDetail(isVisible: Boolean) {
            personaDetail.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

        fun bind(item: UsersDataModel) {
            userName.text = item.userName
            userPhone.text = item.phoneNum.toString()
        }

        init {
            personaDetail.setOnClickListener {
                val intent = Intent(context, ManageUsers::class.java)
                intent.putExtra("userName", userName.text.toString())
                context.startActivity(intent)
            }
        }

        fun showDeleteConfirmationDialog(position: Int) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete User")
            builder.setMessage("Are you sure you want to delete this user?")
            builder.setPositiveButton("Delete") { _, _ ->
                context
            }
            builder.setNegativeButton("Cancel", null)
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

}

