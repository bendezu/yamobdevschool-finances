package com.bendezu.yandexfinances.ui.base

import android.support.annotation.StringRes

interface MvpView {
    fun showMessage(@StringRes resId: Int)
    fun showMessage(message: String)
}