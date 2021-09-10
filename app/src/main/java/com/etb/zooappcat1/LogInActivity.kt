package com.etb.zooappcat1

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LogInActivity : AppCompatActivity() {
    private val prefKey = "prefSecret"

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()//...to hide the AppBar on this activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var loginButton = findViewById<Button>(R.id.SignInButton)

        loginButton.setOnClickListener {
            val userEmail: String = username.text.toString().trim()
            val userPassword: String = password.text.toString().trim()
            if (validateInputs()) {
                httpRequest(userEmail, userPassword)
            }

        }
    }

    private fun validateInputs(): Boolean {
        validateUsername()
        validatePassword()
        return validateUsername() && validatePassword()
    }

    private fun validateUsername(): Boolean {
        val input: EditText = findViewById(R.id.username)
        if (input.text.toString().isEmpty()) {
            input.error = "username id required"
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        val input: EditText = findViewById(R.id.password)
        if (input.text.toString().isEmpty()) {
            input.error = "password required"
            return false
        }
        return true
    }

    private fun httpRequest(username: String, password: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://mydomainetb.xyz/CongoNationalZoo/mobile_api/user/login.php"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            prefKey, Context.MODE_PRIVATE
        )

        val progress = ProgressDialog(this)
        progress.setMessage("Please Wait...")
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.show()

        val stringReq: StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->

                    progress.hide()

                    val jsonObjectFormat = JSONObject(response)
                    val success = jsonObjectFormat.getString("success")
                    /*

                    */

                    when {
                        success.equals("1") -> {

                            val currentEmail = jsonObjectFormat.getString("emailAddress")
                            val currentNames = jsonObjectFormat.getString("Name")

                            // for storing the email and the name received whey the user has logined in
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putString("emailAddress", currentEmail.toString())
                            editor.putString("Name", currentNames.toString())
                            editor.apply()
                            editor.commit()

                            startActivity(Intent(this, MainActivity::class.java))
                            overridePendingTransition(0, 0)
                        }
                        success == "0" -> {
                            Toast.makeText(this, "Incorrect username/password", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {
                            Toast.makeText(this, "Account no found", Toast.LENGTH_SHORT).show()
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
                    val map = HashMap<String, String>()
                    map["emailAddress"] = username
                    map["password"] = password
                    return map
                }
            }
        queue.add(stringReq)
    }

}

