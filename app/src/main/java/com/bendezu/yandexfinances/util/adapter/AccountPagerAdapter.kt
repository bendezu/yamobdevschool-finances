package com.bendezu.yandexfinances.util.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.data.model.*
import com.bendezu.yandexfinances.ui.accountFeed.FeedContract
import com.bendezu.yandexfinances.ui.settings.PREF_ALTERNATE_CURRENCY_KEY
import com.bendezu.yandexfinances.ui.settings.PREF_PRIMARY_CURRENCY_KEY
import java.math.RoundingMode

class AccountPagerAdapter(val context: Context, val presenter: FeedContract.Presenter): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.account_item, container, false)

        view.findViewById<TextView>(R.id.accountTitle).text = accounts[position]
        val primaryBalance = view.findViewById<TextView>(R.id.primaryBalance)
        val alternateBalance = view.findViewById<TextView>(R.id.alternateBalance)

        val preferences = context.getSharedPreferences("", Context.MODE_PRIVATE)
        val primaryCurrencyId = preferences.getInt(PREF_PRIMARY_CURRENCY_KEY, 0)
        val alternateCurrencyId = preferences.getInt(PREF_ALTERNATE_CURRENCY_KEY, 0)

        calculateBalance(records, position, primaryCurrencyId) {
            if (it != null) {
                var rounded = it.setScale(2, RoundingMode.HALF_UP).toPlainString()
                primaryBalance.text = "$rounded ${currencies[primaryCurrencyId].symbol}"

                convertBalance(it, primaryCurrencyId, alternateCurrencyId) {
                    rounded = it.setScale(2, RoundingMode.HALF_UP).toPlainString()
                    alternateBalance.text = "$rounded ${currencies[alternateCurrencyId].symbol}"
                }
            } else {
                //error
                Toast.makeText(context, context.getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<ImageView>(R.id.diagramButton).setOnClickListener {
            presenter.onDiagramClicked(position)
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return accounts.size
    }

}