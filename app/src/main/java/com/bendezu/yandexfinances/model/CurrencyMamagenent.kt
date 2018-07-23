package com.bendezu.yandexfinances.model

import java.math.BigDecimal

fun getPrimaryCurrency() = "RUB"
fun getAlternateCurrency() = "USD"

class AccountRepository {
    fun getUsdBalance() = BigDecimal("12.34")
}
class ExchangeRepository {
    fun getRateCoef(currency: String)  =
            when (currency) {
                "USD" -> BigDecimal(1)
                "RUB" -> BigDecimal(60)
                else -> BigDecimal(0)
            }
}