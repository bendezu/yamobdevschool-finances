package com.bendezu.yandexfinances.model

import java.math.BigDecimal
import java.math.MathContext

enum class OperationType {
    OPERATION_WITHDRAW_TYPE,
    OPERATION_DEPOSIT_TYPE
}

class Record(val type: OperationType, val amount: BigDecimal, val currency: String)

fun calculateBalanceInUsd(records: List<Record>): BigDecimal {
    val repository = ExchangeRepository()
    var sum = BigDecimal(0)
    records.forEach{
        val value = it.amount.divide(repository.getRateCoef(it.currency), MathContext.DECIMAL128)
        if (it.type == OperationType.OPERATION_DEPOSIT_TYPE)
            sum += value
        if (it.type == OperationType.OPERATION_WITHDRAW_TYPE)
            sum -= value
    }
    return sum
}