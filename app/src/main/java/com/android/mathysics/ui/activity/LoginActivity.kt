package com.android.mathysics.ui.activity

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

        var data = User("rachma","rachma")
        var username: String = data.username
        var password: String = data.password

        etUsername.addTextChangedListener(addDataUsername)
        etPassword.addTextChangedListener(addDataPassword)

        buttonLogin.setOnClickListener{
            if(etUsername.text?.isEmpty()!! && etPassword.text?.isEmpty()!!) {
                Toast.makeText(this, "Empty Username & Password", Toast.LENGTH_SHORT).show()
            }
            else if(etUsername.text?.isEmpty()!!) {
                Toast.makeText(this, "Empty Username", Toast.LENGTH_SHORT).show()
            }
            else if(etPassword.text?.isEmpty()!!) {
                Toast.makeText(this, "Empty Password", Toast.LENGTH_SHORT).show()
            }
            else if(etPassword.text.toString().length < 6) {
                Toast.makeText(this, "Password kurang dari 6", Toast.LENGTH_SHORT).show()
            }
            else if(etUsername.text.toString().equals(username)
                && etPassword.text.toString().equals(password)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Username", username)
                startActivity(intent)
                finish()
            }
            else {
                Snackbar.make(it, "Username atau Password salah", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private var addDataUsername: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            var textUsername = etUsername.text.toString().trim()

            if(textUsername.isEmpty()){
                alertUsername.text = "Required"
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
        override fun afterTextChanged(s: Editable?) {
            var textPassword = etPassword.text.toString().trim()

            if(textPassword.isEmpty()) {
                alertPassword.text = "Required"
                alertPassword.visibility = View.VISIBLE
            }
            else if(textPassword.length < 6){
                alertPassword.text = "Kurang dari 6"
                alertPassword.visibility = View.VISIBLE
            }
            else {
                alertPassword.visibility = View.INVISIBLE
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}