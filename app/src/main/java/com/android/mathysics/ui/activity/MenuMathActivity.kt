package com.android.mathysics.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.mathysics.R
import kotlinx.android.synthetic.main.activity_menu_math.*

class MenuMathActivity : AppCompatActivity() {
    var unit = arrayOf("KM", "HM", "DAM", "M", "DM", "CM", "MM") // pilihan spinner
    var satuanAwal: String = "" // default
    var satuanAkhir: String = "" // default
    var result: Double = 0.0 // default

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
                satuanAwal = unit.get(position)
            }
        }

        spinnerSatuanAkhir?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                satuanAkhir = unit.get(position)
            }
        }

        buttonConvert.setOnClickListener{
            if(etPanjang.text?.isEmpty()!!) {
                Toast.makeText(this, "Empty Panjang", Toast.LENGTH_SHORT).show()
            }
            else {
                calculate()
            }
        }

        buttonResetConvert.setOnClickListener{
            reset()
        }
    }

    fun calculate(){
        var value = etPanjang.text.toString().toDouble() // value panjang dari user
        var oldUnit = unit.indexOf(satuanAwal) // satuan lama
        var newUnit = unit.indexOf(satuanAkhir) // satuan baru yang akan dikonversi

        if(oldUnit > newUnit) result = convert("up", newUnit, oldUnit, value)
        else if(oldUnit < newUnit) result = convert("down", newUnit, oldUnit, value)
        else result = value

        resultConvert.text = "$result $satuanAkhir"
    }

    fun convert(convertTo: String, newUnit: Int, oldUnit: Int, value: Double): Double {
        var diff: Int
        var result = 0.0

        if(convertTo.equals("up")) {
            diff = oldUnit - newUnit
            result = value / Math.pow(10.0, diff.toDouble())
        }
        else if(convertTo.equals("down")) {
            diff = newUnit - oldUnit
            result = value * Math.pow(10.0, diff.toDouble())
        }
        return result
    }

    fun reset() {
        resultConvert.text = "0"
        etPanjang.text?.clear()
        etPanjang.clearFocus()
    }
}