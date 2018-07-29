package com.bendezu.yandexfinances.model

import java.math.BigDecimal

fun getPrimaryCurrency() = currencies[0] //RUB
fun getAlternateCurrency() = currencies[1] //USD

class AccountRepository {
    fun getUsdBalance() = BigDecimal("12.34")
}
class ExchangeRepository {
    fun getRateCoef(currency: Currency)  =
            when (currency.label) {
                "USD" -> BigDecimal(1)
                "RUB" -> BigDecimal(60)
                else -> BigDecimal(0)
            }
}