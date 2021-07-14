package com.etb.zooappcat1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()//to hide the AppBar on this activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var loginButton = findViewById<Button>(R.id.SignInButton)
        val hardCodedUsername: String = "12345"
        val hardCodedPassword: String = "12345"

        loginButton.setOnClickListener {
            if (username.text.toString() == hardCodedUsername
                &&
                password.text.toString() == hardCodedPassword
            ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else if (username.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
                val toast = Toast.makeText(
                    this,
                    "no username or password provided",
                    Toast.LENGTH_LONG
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    this,
                    "username or password incorrect !\nPlease try again.",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }

        }
    }
}