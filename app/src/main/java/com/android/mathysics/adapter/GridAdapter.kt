package com.android.mathysics.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.mathysics.R
import com.android.mathysics.data.Menu

class GridAdapter (private val context: Context, private val data: List<Menu>?): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_menu, parent, false)
        val menuTitle = view.findViewById<TextView>(R.id.title)
        val menuImage = view.findViewById<ImageView>(R.id.image)
        val item = data?.get(position)

        menuTitle.text = item?.title
        menuImage.setImageResource(item?.image?: 0)

        return view
    }

    override fun getItem(position: Int): Any = data?.get(position)?: 0

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = data?.size?: 0
}