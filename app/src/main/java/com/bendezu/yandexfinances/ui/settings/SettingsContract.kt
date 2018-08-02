package com.bendezu.yandexfinances.ui.settings

import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.base.MvpPresenter
import com.bendezu.yandexfinances.ui.base.MvpView

interface SettingsContract {
    interface View: MvpView {
        fun selectPrimaryCurrency(currencyId: Int)
        fun selectAlternateCurrency(currencyId: Int)
    }

    @FragmentScope
    interface Presenter<in V: MvpView>: MvpPresenter<V> {
        fun setupUI()
        fun savePrimaryCurrency(currencyId: Int)
        fun saveAlternateCurrency(currencyId: Int)
    }
}