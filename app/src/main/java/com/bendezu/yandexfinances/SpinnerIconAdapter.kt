package com.bendezu.yandexfinances

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class SpinnerIconAdapter(val inflater: LayoutInflater, val labels: Array<String>, val icons: Array<Int>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = inflater.inflate(R.layout.spinner_item, null)
            view.findViewById<ImageView>(R.id.icon).setImageResource(icons[position])
            view.findViewById<TextView>(R.id.title).text = labels[position]
            return view
    }

    override fun getItem(p0: Int): Any = labels[p0]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = labels.size

}