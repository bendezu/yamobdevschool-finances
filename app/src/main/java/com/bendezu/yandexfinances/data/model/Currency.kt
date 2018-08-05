package com.bendezu.yandexfinances.data.model

import android.support.annotation.DrawableRes

data class Currency(val label: String, @DrawableRes val icon: Int, val symbol: Char)