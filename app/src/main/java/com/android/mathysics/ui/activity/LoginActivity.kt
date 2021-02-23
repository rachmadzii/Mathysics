package com.android.mathysics.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.android.mathysics.R
import com.android.mathysics.data.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val data = User("rachma","rachma")
        val username: String = data.username
        val password: String = data.password

        etUsername.addTextChangedListener(addDataUsername)
        etPassword.addTextChangedListener(addDataPassword)

        buttonLogin.setOnClickListener{
            if(etUsername.text?.isEmpty()!! && etPassword.text?.isEmpty()!!) {
                Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_LONG).show()
            }
            else if(etUsername.text?.isEmpty()!!) {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_LONG).show()
            }
            else if(etPassword.text?.isEmpty()!!) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            }
            else if(etPassword.text.toString().length < 6) {
                Toast.makeText(this, "Password must be at least 6 character", Toast.LENGTH_LONG).show()
            }
            else if(etUsername.text.toString() == username
                && etPassword.text.toString() == password
            ) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Username", username)
                startActivity(intent)
                finish()
            }
            else {
                Snackbar.make(it, "Wrong username or password, try again.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private var addDataUsername: TextWatcher = object : TextWatcher {
        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable?) {
            val textUsername = etUsername.text.toString().trim()

            if(textUsername.isEmpty()){
                alertUsername.text = "Enter an username"
                alertUsername.visibility = View.VISIBLE
            }
            else {
                alertUsername.visibility = View.INVISIBLE
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private var addDataPassword: TextWatcher = object : TextWatcher {
        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable?) {
            val textPassword = etPassword.text.toString().trim()

            when {
                textPassword.isEmpty() -> {
                    alertPassword.text = "Enter a password"
                    alertPassword.visibility = View.VISIBLE
                }
                textPassword.length < 6 -> {
                    alertPassword.text = "Password must be at least 6 character"
                    alertPassword.visibility = View.VISIBLE
                }
                else -> {
                    alertPassword.visibility = View.INVISIBLE
                }
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}