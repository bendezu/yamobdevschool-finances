package com.bendezu.yandexfinances.settings

interface SettingsContract {
    interface View {
        fun selectPrimaryCurrency(currencyId: Int)
        fun selectAlternateCurrency(currencyId: Int)
    }

    interface Presenter {
        fun setupUI()
        fun savePrimaryCurrency(currencyId: Int)
        fun saveAlternateCurrency(currencyId: Int)
        fun detachView()
    }
}