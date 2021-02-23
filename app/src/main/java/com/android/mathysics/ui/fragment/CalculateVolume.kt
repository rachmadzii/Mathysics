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
            when {
                etPanjangVolume.text?.isEmpty()!! -> {
                    Toast.makeText(context, "Length value cannot be empty", Toast.LENGTH_SHORT).show()
                }
                etLebarVolume.text?.isEmpty()!! -> {
                    Toast.makeText(context, "Width value cannot be empty", Toast.LENGTH_SHORT).show()
                }
                etTinggiVolume.text?.isEmpty()!! -> {
                    Toast.makeText(context, "Height value cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    calculate()
                }
            }
        }

        view.buttonResetVolume.setOnClickListener{
            reset()
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        val length = etPanjangVolume.text.toString().toDouble()
        val width = etLebarVolume.text.toString().toDouble()
        val height = etTinggiVolume.text.toString().toDouble()
        val calculate = length * width * height
        val result = String.format("%.2f", calculate)

        resultVolume.text = "$result M3"
    }

    @SuppressLint("SetTextI18n")
    private fun reset() {
        resultVolume.text = "0 M3"
        etPanjangVolume.text?.clear()
        etLebarVolume.text?.clear()
        etTinggiVolume.text?.clear()
        etPanjangVolume.clearFocus()
        etLebarVolume.clearFocus()
        etTinggiVolume.clearFocus()
    }
}