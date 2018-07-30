package com.bendezu.yandexfinances.addRecord

import com.bendezu.yandexfinances.model.Currency

interface AddRecordContract {
    interface View {
        fun updateRecordInfo(info: String)
    }

    interface Presenter {
        fun setupUI()
        fun detachView()
        fun onAmountChanged(amount: String)
        fun onRecordTypeChanged(isChecked: Boolean)
        fun onCurrencyChanged(currency: Currency)
    }
}