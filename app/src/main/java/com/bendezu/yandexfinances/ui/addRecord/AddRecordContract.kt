package com.bendezu.yandexfinances.ui.addRecord

import com.bendezu.yandexfinances.data.model.Currency
import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.base.MvpPresenter
import com.bendezu.yandexfinances.ui.base.MvpView

interface AddRecordContract {
    interface View: MvpView {
        fun updateRecordInfo(info: String)
        fun setPrimaryCurrency(primaryCurrencyId: Int)
    }

    @FragmentScope
    interface Presenter<in V: View>: MvpPresenter<V> {
        fun setupUI()
        fun onAmountChanged(amount: String)
        fun onRecordTypeChanged(isChecked: Boolean)
        fun onCurrencyChanged(currency: Currency)
    }
}