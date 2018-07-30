package com.bendezu.yandexfinances.settings

import android.content.SharedPreferences

const val PREF_PRIMARY_CURRENCY_KEY = "primary_currency"
const val PREF_ALTERNATE_CURRENCY_KEY = "alternate_currency"

class SettingsFragmentPresenter(var view: SettingsContract.View?,
                                private val preferences: SharedPreferences) : SettingsContract.Presenter {
    override fun setupUI() {
        val primary = preferences.getInt(PREF_PRIMARY_CURRENCY_KEY, 0)
        val alternate = preferences.getInt(PREF_ALTERNATE_CURRENCY_KEY, 0)
        view?.selectPrimaryCurrency(primary)
        view?.selectAlternateCurrency(alternate)
    }

    override fun savePrimaryCurrency(currencyId: Int) {
        preferences.edit().putInt(PREF_PRIMARY_CURRENCY_KEY, currencyId).apply()
    }

    override fun saveAlternateCurrency(currencyId: Int) {
        preferences.edit().putInt(PREF_ALTERNATE_CURRENCY_KEY, currencyId).apply()
    }

    override fun detachView() {
        view = null
    }


}