package com.bendezu.yandexfinances.util

import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.model.Record
import com.bendezu.yandexfinances.model.RecordType
import java.math.BigDecimal

val currencies = arrayOf(
        "RUB",
        "USD",
        "EUR")
val currencyIds = arrayOf(
        R.drawable.russia,
        R.drawable.usa,
        R.drawable.european_union)


val categories = arrayOf(
        "Not categorized",
        "Food",
        "Clothes",
        "Rent fee")
val categoryIds = arrayOf(
        R.drawable.ic_no_category_24dp,
        R.drawable.ic_food_24dp,
        R.drawable.ic_clothes_24dp,
        R.drawable.ic_rent_fee_24dp)

val accounts = arrayOf("Debit Card", "Cash")

val records = arrayOf(
        Record(0, RecordType.INCOME, BigDecimal(10), "USD"),
        Record(0, RecordType.EXPENSE, BigDecimal(5), "USD"),
        Record(0, RecordType.INCOME, BigDecimal(10), "USD"),

        Record(1, RecordType.INCOME, BigDecimal(20), "USD"),
        Record(1, RecordType.EXPENSE, BigDecimal(10), "USD"),
        Record(1, RecordType.INCOME, BigDecimal(20), "USD")
)
