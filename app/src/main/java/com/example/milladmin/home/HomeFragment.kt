package com.example.milladmin.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milladmin.Database.MillData
import com.example.milladmin.GetMills
import com.example.milladmin.R
import com.example.milladmin.adapters.ItemAdapter
import com.example.milladmin.databinding.FragmentNav1Binding

class HomeFragment : Fragment() {

    private var _binding: FragmentNav1Binding? = null
    private val binding get() = _binding!!
    private val ADD_MILL_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNav1Binding.inflate(inflater, container, false)

        // Showing Title
        val textView: TextView = binding.root.findViewById(R.id.textView)
        textView.text = "Mill Management"

        // Add Mill Intent
        val add_mill: Button = binding.root.findViewById(R.id.add_mill)
        add_mill.setOnClickListener {
            val intent = Intent(requireContext(), PlusMill::class.java)
            startActivityForResult(intent,ADD_MILL_REQUEST_CODE)
        }

        // ItemAdapter
        binding.recyclerViewItem.layoutManager = LinearLayoutManager(requireContext())
        getMillList()


        return binding.root
    }

    //Get List
    private fun getMillList() {
        GetMills(object : GetMills.OnDataFetchedListener {
            override fun onDataFetched(data: List<MillData>) {
                val itemAdapter = ItemAdapter(requireContext(), data)
                Log.e("MillList", "$data")
                binding.recyclerViewItem.adapter = itemAdapter
            }
        }).execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_MILL_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            // Refresh the user list when returning from PlusUser activity
            getMillList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
