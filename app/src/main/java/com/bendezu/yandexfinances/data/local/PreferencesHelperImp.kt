package com.bendezu.yandexfinances.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesHelperImp @Inject constructor(context: Context): PreferencesHelper {
    companion object {
        const val PREF_PRIMARY_CURRENCY_KEY = "primary_currency"
        const val PREF_ALTERNATE_CURRENCY_KEY = "alternate_currency"
    }

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("prefs", MODE_PRIVATE)

    override fun getPrimaryCurrency(): Int = sharedPreferences.getInt(PREF_PRIMARY_CURRENCY_KEY, 0)
    override fun getAlternateCurrency(): Int = sharedPreferences.getInt(PREF_ALTERNATE_CURRENCY_KEY, 0)

    override fun setPrimaryCurrency(currencyId: Int) {
        sharedPreferences.edit()?.putInt(PREF_PRIMARY_CURRENCY_KEY, currencyId)?.apply()
    }

    override fun setSecondaryCurrency(currencyId: Int) {
        sharedPreferences.edit()?.putInt(PREF_ALTERNATE_CURRENCY_KEY, currencyId)?.apply()
    }
}