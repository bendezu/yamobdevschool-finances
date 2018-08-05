package com.bendezu.yandexfinances.data.local

import javax.inject.Singleton

@Singleton
interface PreferencesHelper {
    fun getPrimaryCurrency(): Int
    fun getAlternateCurrency(): Int

    fun setPrimaryCurrency(currencyId: Int)
    fun setSecondaryCurrency(currencyId: Int)
}