package com.bendezu.yandexfinances.data.model

import java.math.BigDecimal

data class Record(val accountId: Int,
                  val type: RecordType,
                  val amount: BigDecimal,
                  val currencyId: Int,
                  val categoryId: Int)