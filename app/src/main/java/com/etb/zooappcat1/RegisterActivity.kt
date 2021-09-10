package com.etb.zooappcat1

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

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
                httpRequest(
                    firstName.text.toString().trim(),
                    secondName.text.toString().trim(),
                    phoneNumber.text.toString().trim(),
                    email.text.toString().trim(),
                    password.text.toString().trim()
                )
            }
        }
    }

    private fun httpRequest(
        firstName: String,
        secondName: String,
        phoneNumber: String,
        email: String,
        password: String
    ) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://mydomainetb.xyz/CongoNationalZoo/mobile_api/user/register.php"

        val progress = ProgressDialog(this)
        progress.setMessage("Please Wait...")
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.show()

        val stringReq: StringRequest =
            object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->

                    progress.hide()

                    val obj = JSONObject(response)
                    when (obj.getString("success")) {
                        "1" -> {
                            Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, LogInActivity::class.java))
                            overridePendingTransition(0, 0)

                        }
                        "2" -> {
                            Toast.makeText(this, "This account already exists", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {
                            Toast.makeText(this, "try again later", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                Response.ErrorListener { error ->
                    progress.hide()
                    Toast.makeText(
                        this, "Error code: $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["emailAddress"] = email
                    params["firstName"] = firstName
                    params["secondName"] = secondName
                    params["phoneNumber"] = phoneNumber
                    params["password"] = password
                    return params
                }
            }
        queue.add(stringReq)
    }

}


