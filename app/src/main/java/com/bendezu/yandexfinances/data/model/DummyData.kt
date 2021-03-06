package com.bendezu.yandexfinances.data.model

import android.support.annotation.DrawableRes
import com.bendezu.yandexfinances.R
import com.github.mikephil.charting.utils.ColorTemplate.rgb
import java.math.BigDecimal

class Currency(val label: String, @DrawableRes val icon: Int, val symbol: Char)
val currencies = arrayOf(
        Currency("RUB", R.drawable.russia, '\u20BD'),
        Currency("USD", R.drawable.usa, '$'),
        Currency("EUR", R.drawable.european_union, '\u20AC')
)

class Category(val label: String, @DrawableRes val icon: Int, val color: Int)
val categories = arrayOf(
        Category("Not categorized", R.drawable.ic_no_category_24dp, rgb("9e9e9e")),
        Category("Food", R.drawable.ic_food_24dp, rgb("ff8f00")),
        Category("Clothes", R.drawable.ic_clothes_24dp, rgb("ff0000")),
        Category("Rent fee", R.drawable.ic_rent_fee_24dp, rgb("7cb342"))
)

val accounts = arrayOf("Debit Card", "Cash")

enum class RecordType {
    EXPENSE,
    INCOME
}
class Record(val accountId: Int, val type: RecordType, val amount: BigDecimal, val currencyId: Int, val categoryId: Int)
val records = arrayOf(
        Record(0, RecordType.INCOME, BigDecimal(10), 1, 0),
        Record(0, RecordType.EXPENSE, BigDecimal(5), 1, 1),
        Record(0, RecordType.INCOME, BigDecimal(10), 1, 0),
        Record(0, RecordType.EXPENSE, BigDecimal(1), 1, 2),
        Record(0, RecordType.EXPENSE, BigDecimal(2), 1, 2),
        Record(0, RecordType.EXPENSE, BigDecimal(2), 1, 3),
        Record(0, RecordType.EXPENSE, BigDecimal(3), 1, 1),

        Record(1, RecordType.INCOME, BigDecimal(20), 1, 0),
        Record(1, RecordType.EXPENSE, BigDecimal(10), 1, 3),
        Record(1, RecordType.INCOME, BigDecimal(20), 1, 0)
)
