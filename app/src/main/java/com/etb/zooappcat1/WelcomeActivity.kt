package com.etb.zooappcat1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()//to hide the AppBar on this activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val signInBn = findViewById<Button>(R.id.SignInBn)
        val registerBn = findViewById<Button>(R.id.RegisterBn)

        registerBn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        signInBn.setOnClickListener {
            val intent = Intent(applicationContext, LogInActivity::class.java)
            startActivity(intent)
        }

    }
}