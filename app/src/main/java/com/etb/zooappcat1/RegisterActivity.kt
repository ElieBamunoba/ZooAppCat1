package com.etb.zooappcat1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var firstName = findViewById<EditText>(R.id.firstName)
        var secondName = findViewById<EditText>(R.id.secondName)
        var password = findViewById<EditText>(R.id.password)
        var password2 = findViewById<EditText>(R.id.password2)
        var email = findViewById<EditText>(R.id.emailAddress)
        var phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val signUpButton = findViewById<Button>(R.id.SignUp)

        signUpButton.setOnClickListener {
            if (firstName.text.isNullOrBlank() ||
                secondName.text.isNullOrBlank() ||
                email.text.isNullOrBlank() ||
                password.text.isNullOrBlank() ||
                password2.text.isNullOrBlank() ||
                phoneNumber.text.isNullOrBlank()
            ) {
                val toast = Toast.makeText(
                    this,
                    "Please fill all the required fields",
                    Toast.LENGTH_LONG
                )
                toast.show()
            } else if (password.text.toString() != password2.text.toString()) {
                val toast = Toast.makeText(
                    this,
                    "passwords do not match! ",
                    Toast.LENGTH_LONG
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    this,
                    "Registration successfully completed !",
                    Toast.LENGTH_LONG
                )
                toast.show()
                intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
            }
        }
    }
}