package com.android.mathysics.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.android.mathysics.R
import com.android.mathysics.ui.activity.LoginActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        val delay = 1000
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, delay.toLong())
    }
}