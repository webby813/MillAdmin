package com.example.milladmin.home

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.milladmin.Database.MillData
import com.example.milladmin.Database.fetchedMillData
import com.example.milladmin.Database.newMillData
import com.example.milladmin.FetchDataAsHint
import com.example.milladmin.GetMills
import com.example.milladmin.HomeActivity
//import com.example.milladmin.FetchDataAsHint
import com.example.milladmin.R

class ManageMill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_managemill)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Receive data from intent & set the received millName as the hint for newMill EditText
        val PiID = intent.getStringExtra("PiID")
        val newMillEditText = findViewById<EditText>(R.id.newPiid)
        val Name = intent.getStringExtra("name")
        val newMillName = findViewById<EditText>(R.id.newMill)
        val Status = intent.getStringExtra("status")
        val newMillStatusEditText = findViewById<EditText>(R.id.newStatus)
        val SteamPress = intent.getStringExtra("steamPress")
        val newSteamPress = findViewById<EditText>(R.id.newStmPress)
        val SteamFlow = intent.getStringExtra("SteamFlow")
        val newSteamFlow = findViewById<EditText>(R.id.newStmFlow)
        val WtrLevel = intent.getStringExtra("WtrLevel")
        val newWtrLevel = findViewById<EditText>(R.id.newWtrLevel)
        val PwrFrq = intent.getStringExtra("PwrFrq")
        val newPwrFrq = findViewById<EditText>(R.id.newPwrFq)
        val Mode = intent.getStringExtra("mode")
        val newMode = findViewById<EditText>(R.id.newMode)
        val proxyPort = intent.getStringExtra("proxyport")
        val newProxyPort = findViewById<EditText>(R.id.newProxy)
        val TunnelPort = intent.getStringExtra("tunnelport")
        val newTunnel = findViewById<EditText>(R.id.newTunnel)

        newMillEditText.hint = PiID
        newMillName.hint = Name
        newMillStatusEditText.hint = Status
        newSteamPress.hint = SteamPress
        newSteamFlow.hint = SteamFlow
        newWtrLevel.hint = WtrLevel
        newPwrFrq.hint = PwrFrq
        newMode.hint = Mode
        newProxyPort.hint = proxyPort
        newTunnel.hint = TunnelPort

    }

}
