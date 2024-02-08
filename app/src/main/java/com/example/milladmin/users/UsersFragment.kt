package com.example.milladmin.users

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milladmin.Database.UserData
import com.example.milladmin.GetUsers
import com.example.milladmin.R
import com.example.milladmin.adapters.UsersAdapter
import com.example.milladmin.databinding.FragmentNav2Binding

class UsersFragment : Fragment() {

    private var _binding: FragmentNav2Binding? = null
    private val binding get() = _binding!!
    private val ADD_USER_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNav2Binding.inflate(inflater, container, false)

        //Showing Title
        val textView: TextView = binding.root.findViewById(R.id.textView)
        textView.text = "User Management"

        //add_user intent
        val add_user : Button = binding.root.findViewById(R.id.add_user)
        add_user.setOnClickListener {
            val intent = Intent(requireContext(), PlusUser::class.java)
            startActivityForResult(intent, ADD_USER_REQUEST_CODE)
        }

        //ItemAdapter
        binding.recyclerViewItem.layoutManager = LinearLayoutManager(requireContext())
        getUsersList()

        return binding.root
    }


    private fun getUsersList(){
        GetUsers(object : GetUsers.OnDataFetchedListener{
            override fun onDataFetched(data: List<UserData>) {
                val usersAdapter = UsersAdapter(requireContext(), data)
                binding.recyclerViewItem.adapter = usersAdapter
            }
        }).execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_USER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            // Refresh the user list when returning from PlusUser activity
            getUsersList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}