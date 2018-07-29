package com.bendezu.yandexfinances

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bendezu.yandexfinances.model.Currency

class CurrencySpinnerAdapter(private val inflater: LayoutInflater, private val currencies: Array<Currency>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = inflater.inflate(R.layout.spinner_item, null)
            view.findViewById<ImageView>(R.id.icon).setImageResource(currencies[position].icon)
            view.findViewById<TextView>(R.id.title).text = currencies[position].label
            return view
    }

    override fun getItem(p0: Int): Any = currencies[p0].label

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = currencies.size

}