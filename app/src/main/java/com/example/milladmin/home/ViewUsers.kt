package com.example.milladmin.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milladmin.Database.UserData
import com.example.milladmin.GetUsers
import com.example.milladmin.R
import com.example.milladmin.adapters.UsersAdapter
import com.example.milladmin.adapters.MillViewUsers

class ViewUsers : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewusers)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        recyclerView = findViewById(R.id.recycler_view_users)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Call the function to get the list of users
        getUsersList()
    }

    private fun getUsersList() {
        GetUsers(object : GetUsers.OnDataFetchedListener {
            override fun onDataFetched(data: List<UserData>) {
                // Create and set up the adapter with the fetched data
                usersAdapter = UsersAdapter(this@ViewUsers, data)
                recyclerView.adapter = usersAdapter
            }
        }).execute()
    }

    fun onDeleteUserConfirmed(position: Int) {
        // Handle delete user from ViewUsers logic
        // ...
        println("Deleting user from ViewUsers at position: $position")
    }
}
