package com.android.mathysics.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.mathysics.R
import kotlinx.android.synthetic.main.fragment_calculate_volume.*
import kotlinx.android.synthetic.main.fragment_calculate_volume.view.*

class CalculateVolume : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_calculate_volume, container, false)

        view.buttonCalculateVolume.setOnClickListener{
            if(etPanjangVolume.text?.isEmpty()!!) {
                Toast.makeText(context, "Empty Panjang", Toast.LENGTH_SHORT).show()
            }
            else if(etLebarVolume.text?.isEmpty()!!) {
                Toast.makeText(context, "Empty Lebar", Toast.LENGTH_SHORT).show()
            }
            else if(etTinggiVolume.text?.isEmpty()!!) {
                Toast.makeText(context, "Empty Tinggi", Toast.LENGTH_SHORT).show()
            }
            else {
                calculate()
            }
        }

        view.buttonResetVolume.setOnClickListener{
            reset()
        }

        return view
    }

    fun calculate() {
        val length = etPanjangVolume.text.toString().toDouble()
        val width = etLebarVolume.text.toString().toDouble()
        val height = etTinggiVolume.text.toString().toDouble()
        val calculate = length * width * height
        val result = String.format("%.2f", calculate)

        resultVolume.text = "$result M3"
    }

    fun reset() {
        resultVolume.text = "0 M3"
        etPanjangVolume.text?.clear()
        etLebarVolume.text?.clear()
        etTinggiVolume.text?.clear()
        etPanjangVolume.clearFocus()
        etLebarVolume.clearFocus()
        etTinggiVolume.clearFocus()
    }
}