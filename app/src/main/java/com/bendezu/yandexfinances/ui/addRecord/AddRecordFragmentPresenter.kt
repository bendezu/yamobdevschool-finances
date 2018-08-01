package com.bendezu.yandexfinances.ui.addRecord

import com.bendezu.yandexfinances.data.model.Currency
import com.bendezu.yandexfinances.data.model.RecordType
import com.bendezu.yandexfinances.data.model.currencies
import java.math.BigDecimal
import java.math.RoundingMode

class AddRecordFragmentPresenter(var view: AddRecordContract.View?) :AddRecordContract.Presenter {

    override fun setupUI() {
        updateRecordInfo()
    }

    private var type: RecordType = RecordType.EXPENSE
    private var amount: String = "0"
        get() = if (field == "") "0" else BigDecimal(field).setScale(2, RoundingMode.HALF_UP).toPlainString()
    private var currency: Currency = currencies[0]

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

    override fun onCurrencyChanged(currency: Currency) {
        this.currency = currency
        updateRecordInfo()
    }

    private fun updateRecordInfo(){
        val sign: String = if (type == RecordType.EXPENSE) "-" else "+"
        view?.updateRecordInfo("$sign $amount ${currency.symbol}")
    }

}