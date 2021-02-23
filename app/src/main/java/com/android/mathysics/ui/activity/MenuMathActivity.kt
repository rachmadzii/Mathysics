package com.android.mathysics.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.mathysics.R
import kotlinx.android.synthetic.main.activity_menu_math.*
import kotlin.math.pow

class MenuMathActivity : AppCompatActivity() {
    private var unit = arrayOf("KM", "HM", "DAM", "M", "DM", "CM", "MM") // pilihan spinner
    private var satuanAwal: String = "" // default
    private var satuanAkhir: String = "" // default
    private var result: Double = 0.0 // default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_math)
        supportActionBar?.hide()

        val arrayAdapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, unit)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSatuanAwal.adapter = arrayAdapter
        spinnerSatuanAkhir.adapter = arrayAdapter

        spinnerSatuanAwal?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                satuanAwal = unit[position]
            }
        }

        spinnerSatuanAkhir?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                satuanAkhir = unit[position]
            }
        }

        buttonConvert.setOnClickListener{
            if(etPanjang.text?.isEmpty()!!) {
                Toast.makeText(this, "Length value cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else {
                calculate()
            }
        }

        buttonResetConvert.setOnClickListener{
            reset()
        }

        buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    fun calculate(){
        val value = etPanjang.text.toString().toDouble() // value panjang dari user
        val oldUnit = unit.indexOf(satuanAwal) // satuan lama
        val newUnit = unit.indexOf(satuanAkhir) // satuan baru yang akan dikonversi

        result = when {
            oldUnit > newUnit -> convert("up", newUnit, oldUnit, value)
            oldUnit < newUnit -> convert("down", newUnit, oldUnit, value)
            else -> value
        }

        resultConvert.text = "$result $satuanAkhir"
    }

    private fun convert(convertTo: String, newUnit: Int, oldUnit: Int, value: Double): Double {
        val diff: Int
        var result = 0.0

        if(convertTo == "up") {
            diff = oldUnit - newUnit
            result = value / 10.0.pow(diff.toDouble())
        }
        else if(convertTo == "down") {
            diff = newUnit - oldUnit
            result = value * 10.0.pow(diff.toDouble())
        }
        return result
    }

    private fun reset() {
        resultConvert.text = "0"
        etPanjang.text?.clear()
        etPanjang.clearFocus()
    }
}