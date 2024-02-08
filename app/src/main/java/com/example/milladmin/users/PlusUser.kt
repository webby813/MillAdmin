package com.example.milladmin.users

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.milladmin.CreateUser
import com.example.milladmin.R
import com.example.milladmin.Database.newUserData

class PlusUser : AppCompatActivity(), CreateUser.OnUserCreatedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plususer)
        val toolbarU: Toolbar = findViewById(R.id.toolbarU)
        setSupportActionBar(toolbarU)

        val createNewUser: Button = findViewById(R.id.button)
        val newUsername = findViewById<EditText>(R.id.newUsername)
        val newPhoneNum = findViewById<EditText>(R.id.newPhoneno)
        val newStatus = findViewById<EditText>(R.id.newStatus)
        val newRole = findViewById<EditText>(R.id.newRole)
        val newState = findViewById<EditText>(R.id.newStmFlow)
        val newStmFlowUser = findViewById<EditText>(R.id.newStmFlowUser)
        val newWtrLevel = findViewById<EditText>(R.id.newWtrLevel)
        val newPwrFq = findViewById<EditText>(R.id.newPwrFq)
        val newStmPressUser = findViewById<EditText>(R.id.NewStmPressUser)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbarU.setNavigationOnClickListener {
            onBackPressed()
        }

        fun clearAllEditTextFields() {
            newUsername.text.clear()
            newPhoneNum.text.clear()
            newStatus.text.clear()
            newRole.text.clear()
            newState.text.clear()
            newStmFlowUser.text.clear()
            newWtrLevel.text.clear()
            newPwrFq.text.clear()
            newStmPressUser.text.clear()
        }

        createNewUser.setOnClickListener {
            // Retrieve data from EditText fields
            val username = newUsername.text.toString()
            val phoneNum = newPhoneNum.text.toString()
            val status = newStatus.text.toString()
            val role = newRole.text.toString()
            val state = newState.text.toString()

            // Create new user data object
            val newUserData = newUserData(
                name = username,
                phoneNum = phoneNum,
                status = status,
                role = role,
                state = state
            )

            // Create and execute CreateUser AsyncTask
            val createUserTask = CreateUser(this)
            createUserTask.execute(newUserData)
            clearAllEditTextFields()
            onBackPressed()
        }
    }



    // Implementation of OnUserCreatedListener
    override fun onUserCreated(success: Boolean) {
        if (success) {
            // User created successfully, show a success message
            Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
            // You can also navigate to another activity or perform other actions if needed
        } else {
            // User creation failed, show an error message
            Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()

        }
    }

}
