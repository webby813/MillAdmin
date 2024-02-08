package com.example.milladmin.home

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.milladmin.CreateMill
import com.example.milladmin.Database.newMillData
import com.example.milladmin.R

class PlusMill : AppCompatActivity(), CreateMill.OnMillCreatedListener{

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plusmill)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val createNewMill: Button = findViewById(R.id.button)
        val newPiId = findViewById<EditText>(R.id.newPiid)
        val newMillName = findViewById<EditText>(R.id.newMill)
        val newStatus = findViewById<EditText>(R.id.newStatus)
        val newStmPress = findViewById<EditText>(R.id.newStmPress)
        val newStmFlow = findViewById<EditText>(R.id.newStmFlow)
        val newWtrLevel = findViewById<EditText>(R.id.newWtrLevel)
        val newPwrFrq = findViewById<EditText>(R.id.newPwrFq)
        val newMode = findViewById<EditText>(R.id.newMode)
        val newProxy = findViewById<EditText>(R.id.newProxy)
        val newTunnel = findViewById<EditText>(R.id.newTunnel)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        fun clearAllEditTextFields() {
            newPiId.text.clear()
            newMillName.text.clear()
            newStatus.text.clear()
            newStmPress.text.clear()
            newStmFlow.text.clear()
            newWtrLevel.text.clear()
            newPwrFrq.text.clear()
            newMode.text.clear()
            newProxy.text.clear()
            newTunnel.text.clear()
        }

        createNewMill.setOnClickListener{
            val PiId = newPiId.text.toString()
            val millName = newMillName.text.toString()
            val status = newStatus.text.toString()
            val stmPress = newStmPress.text.toString()
            val stmFlow = newStmFlow.text.toString()
            val wtrLevel = newWtrLevel.text.toString()
            val pwrFrq = newPwrFrq.text.toString()
            val mode = newMode.text.toString()
            val proxy = newProxy.text.toString()
            val tunnel = newTunnel.text.toString()

            // Create new mill data object
            val newMillData = newMillData(
                PiID = PiId,
                name = millName,
                status = status,
                steamPress = stmPress,
                SteamFlow = stmFlow,
                WtrLevel = wtrLevel,
                PwrFrq = pwrFrq,
                mode = mode,
                proxyport = proxy,
                tunnelport = tunnel
            )

            val createMillTask = CreateMill(this)
            createMillTask.execute(newMillData)
            setResult(Activity.RESULT_OK)
            clearAllEditTextFields()
            onBackPressed()
        }
    }

    override fun onMillCreated(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Mill created successfully", Toast.LENGTH_SHORT).show()
            // Set the result to notify HomeFragment that a mill was created successfully
            setResult(AppCompatActivity.RESULT_OK)
            finish() // Finish the activity to return to HomeFragment
        } else {
            Toast.makeText(this, "Mill created successfully", Toast.LENGTH_SHORT).show()
        }
    }
}

