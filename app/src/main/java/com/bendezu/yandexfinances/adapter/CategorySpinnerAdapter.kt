package com.bendezu.yandexfinances.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.model.Category

class CategorySpinnerAdapter(private val inflater: LayoutInflater, private val categories: Array<Category>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.spinner_item, null)
        view.findViewById<ImageView>(R.id.icon).setImageResource(categories[position].icon)
        view.findViewById<TextView>(R.id.title).text = categories[position].label
        return view
    }

    override fun getItem(p0: Int): Any = categories[p0].label

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = categories.size

}