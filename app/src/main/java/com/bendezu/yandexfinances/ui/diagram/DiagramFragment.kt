package com.bendezu.yandexfinances.ui.diagram

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.data.local.categories
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import kotlinx.android.synthetic.main.fragment_diagram.*


private const val ARG_ACCOUNT_ID = "account_id"

class DiagramFragment : Fragment() {
    private var accountId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            accountId = it.getInt(ARG_ACCOUNT_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diagram, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        toolbar.setNavigationOnClickListener { fragmentManager.popBackStack() }

        val entries = ArrayList<PieEntry>()
        for (id in categories.indices) {
            val amount = (id+1)*10f
            entries.add(PieEntry(amount, context.getDrawable(categories[id].icon)))

            val legendItem = LayoutInflater.from(context).inflate(R.layout.legend_item, legend, false)
            legendItem.findViewById<ImageView>(R.id.legendIcon).apply {
                setImageResource(categories[id].icon)
            }
            legendItem.findViewById<TextView>(R.id.legendLabel).text = categories[id].label
            legendItem.findViewById<TextView>(R.id.legendAmount).text = amount.toString()
            legend.addView(legendItem)
        }
        val set = PieDataSet(entries, "")
        set.colors = categories.map { it.color }
        set.sliceSpace = 4f
        set.setDrawValues(false)

        val data = PieData(set as IPieDataSet)

        val legend = diagram.legend
        legend.isEnabled = false

        diagram.setHoleColor(getColor(context, R.color.colorPrimaryDark))
        diagram.description = null
        diagram.data = data
        diagram.invalidate()

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(accountId: Int) =
                DiagramFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ACCOUNT_ID, accountId)
                    }
                }
    }
}
