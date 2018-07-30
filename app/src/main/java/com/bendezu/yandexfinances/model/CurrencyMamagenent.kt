package com.bendezu.yandexfinances.model

import com.bendezu.yandexfinances.network.convertSync
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.math.BigDecimal
import java.math.MathContext

fun calculateBalance(records: Array<Record>, accountId: Int, targetCurrencyId: Int, callback: (BigDecimal?)->(Unit)) {
    launch {
        try {
            var sum = BigDecimal(0)
            records.filter { it.accountId == accountId }.groupBy { it.currencyId }.map {
                calculateSum(it.value, it.key, targetCurrencyId)
            }.forEach { sum += it }

            launch(UI) {
                callback(sum)
            }
        } catch (e: Exception) {
            launch(UI) {
                callback(null)
            }
        }
    }
}

private fun calculateSum(records: List<Record>, fromCurrencyId: Int, targetCurrencyId: Int): BigDecimal {
    val rate  = convertSync(currencies[fromCurrencyId].label, currencies[targetCurrencyId].label)
    var sum = BigDecimal(0)
    records.forEach {
        if (it.type == RecordType.INCOME)
            sum += it.amount
        if (it.type == RecordType.EXPENSE)
            sum -= it.amount
    }
    return sum.multiply(BigDecimal(rate), MathContext.DECIMAL128)
}

fun convertBalance(balance: BigDecimal, fromCurrencyId: Int, targetCurrencyId: Int, callback: (BigDecimal)->(Unit)) {
    launch {
        val rate = convertSync(currencies[fromCurrencyId].label, currencies[targetCurrencyId].label)
        val converted = balance.multiply(BigDecimal(rate), MathContext.DECIMAL128)

        launch(UI) {
            callback(converted)
        }
    }
}