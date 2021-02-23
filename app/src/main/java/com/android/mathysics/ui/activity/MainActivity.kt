package com.android.mathysics.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.mathysics.R
import com.android.mathysics.adapter.GridAdapter
import com.android.mathysics.data.Menu
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val intent = intent
        val username = intent.getStringExtra("Username")
        textUsername.text = username

        val menu = ArrayList<Menu>()
        menu.add(Menu("Math", R.drawable.ruler))
        menu.add(Menu("Physics", R.drawable.shapes))

        val mainAdapter = GridAdapter(this, menu)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (position == 0) {
                startActivity(Intent(this, MenuMathActivity::class.java))
            }
            else if (position == 1) {
                startActivity(Intent(this, MenuPhysicsActivity::class.java))
            }
        }

        buttonLogout.tooltipText = "Click to sign out"

        buttonLogout.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}