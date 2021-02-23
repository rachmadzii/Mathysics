package com.android.mathysics.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.mathysics.R
import kotlinx.android.synthetic.main.fragment_calculate_area.*
import kotlinx.android.synthetic.main.fragment_calculate_area.view.*

class CalculateArea : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_calculate_area, container, false)

        view.buttonCalculateArea.setOnClickListener{
            when {
                etPanjangArea.text?.isEmpty()!! -> {
                    Toast.makeText(context, "Length value cannot be empty", Toast.LENGTH_SHORT).show()
                }
                etLebarArea.text?.isEmpty()!! -> {
                    Toast.makeText(context, "Width value cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    calculate()
                }
            }
        }

        view.buttonResetArea.setOnClickListener{
            reset()
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        val length = etPanjangArea.text.toString().toDouble()
        val width = etLebarArea.text.toString().toDouble()
        val calculate = length * width
        val result = String.format("%.2f", calculate)

        resultArea.text = "$result M2"
    }

    @SuppressLint("SetTextI18n")
    private fun reset() {
        resultArea.text = "0 M2"
        etPanjangArea.text?.clear()
        etLebarArea.text?.clear()
        etPanjangArea.clearFocus()
        etLebarArea.clearFocus()
    }

    companion object {
        fun newInstance(): CalculateArea {
            val fragment = CalculateArea()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}