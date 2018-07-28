package com.bendezu.yandexfinances.model

import java.math.BigDecimal
import java.math.MathContext

enum class RecordType {
    EXPENSE,
    INCOME
}

class Record(val account: Int, val type: RecordType, val amount: BigDecimal, val currency: String)

fun calculateBalanceInUsd(records: List<Record>, account: Int): BigDecimal {
    val repository = ExchangeRepository()
    var sum = BigDecimal(0)
    records.filter { it.account == account }.forEach {
        val value = it.amount.divide(repository.getRateCoef(it.currency), MathContext.DECIMAL128)
        if (it.type == RecordType.INCOME)
            sum += value
        if (it.type == RecordType.EXPENSE)
            sum -= value
    }
    return sum
}