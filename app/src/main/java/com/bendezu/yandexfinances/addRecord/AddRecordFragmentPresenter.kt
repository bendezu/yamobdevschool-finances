package com.bendezu.yandexfinances.addRecord

import com.bendezu.yandexfinances.model.RecordType
import com.bendezu.yandexfinances.util.currencies

class AddRecordFragmentPresenter(var view: AddRecordContract.View?) :AddRecordContract.Presenter {

    override fun setupUI() {
        updateRecordInfo()
    }

    private var type: RecordType = RecordType.EXPENSE
    private var amount: String = "0"
        get() = if (field != "") field else "0"
    private var currency: String = currencies[0]

    override fun detachView() {
        view = null
    }

    override fun onAmountChanged(amount: String) {
        this.amount = amount
        updateRecordInfo()
    }

    override fun onRecordTypeChanged(isChecked: Boolean) {
        type = if (isChecked) RecordType.INCOME else RecordType.EXPENSE
        updateRecordInfo()
    }

    override fun onCurrencyChanged(currency: String) {
        this.currency = currency
        updateRecordInfo()
    }

    private fun updateRecordInfo(){
        val sign: String = if (type == RecordType.EXPENSE) "-" else "+"
        view?.updateRecordInfo("$sign $amount $currency")
    }

}