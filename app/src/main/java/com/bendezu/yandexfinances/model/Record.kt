package com.bendezu.yandexfinances.model

import java.math.BigDecimal
import java.util.*

enum class OperationType {
    OPERATION_WITHDRAW_TYPE,
    OPERATION_DEPOSIT_TYPE
}

class Record(val type: OperationType, val amount: BigDecimal, val currency: Currency) {
    fun getAmountInUsd(): BigDecimal{
        return BigDecimal(1)
    }
}