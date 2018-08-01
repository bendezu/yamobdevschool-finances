package com.bendezu.yandexfinances.util.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.data.model.Record
import com.bendezu.yandexfinances.data.model.RecordType
import com.bendezu.yandexfinances.data.model.categories
import com.bendezu.yandexfinances.data.model.currencies


class RecordRecyclerViewAdapter(var records: Array<Record>) :
        RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val icon = view.findViewById<ImageView>(R.id.recordIcon)
        val label = view.findViewById<TextView>(R.id.recordLabel)
        val amount = view.findViewById<TextView>(R.id.recordAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecordRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.record_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        val categoryId = record.categoryId
        val currencyId = record.currencyId
        holder.label.text = categories[categoryId].label
        holder.icon.setImageResource(categories[categoryId].icon)

        if (record.type == RecordType.EXPENSE) {
            holder.amount.text = "- ${record.amount.toPlainString()} ${currencies[currencyId].symbol}"
            holder.amount.setTextColor(Color.RED)
        } else {
            holder.amount.text = "+ ${record.amount.toPlainString()} ${currencies[currencyId].symbol}"
            holder.amount.setTextColor(Color.GREEN)
        }

    }

    override fun getItemCount() = records.size
}