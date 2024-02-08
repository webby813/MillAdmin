    package com.example.milladmin.users

    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.appcompat.widget.Toolbar
    import com.example.milladmin.R

    class ManageUsers:AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_manageusers)

            val toolbarU: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbarU)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            toolbarU.setNavigationOnClickListener{
                onBackPressed()
            }
        }


    }