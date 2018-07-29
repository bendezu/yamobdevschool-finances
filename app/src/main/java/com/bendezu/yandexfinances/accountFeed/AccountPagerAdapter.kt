package com.bendezu.yandexfinances.accountFeed

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.model.*

class AccountPagerAdapter(val fragment: FeedFragment): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val inflater = LayoutInflater.from(fragment.context)
        val view = inflater.inflate(R.layout.account_item, container, false)

        view.findViewById<TextView>(R.id.accountTitle).text = accounts[position]
        val primaryBalance = view.findViewById<TextView>(R.id.primaryBalance)
        val alternateBalance = view.findViewById<TextView>(R.id.alternateBalance)
        val balance = calculateBalanceInUsd(records.asList(), position)
        val rateKoef = ExchangeRepository().getRateCoef(getPrimaryCurrency())
        primaryBalance.text = balance.multiply(rateKoef).toPlainString() + " " + getPrimaryCurrency().symbol
        alternateBalance.text = balance.toPlainString() + " " + getAlternateCurrency().symbol
        view.findViewById<ImageView>(R.id.diagramButton).setOnClickListener {
            fragment.presenter.onDiagramClicked(position)
        }

        container?.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, view: Any?) {
        container?.removeView(view as View)
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return accounts.size
    }

}