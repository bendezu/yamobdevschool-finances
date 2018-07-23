package com.bendezu.yandexfinances.accountFeed

interface FeedContract {

    interface View {
        fun setPrimaryBalance(balance: String)
        fun setAlternateBalance(balance: String)
    }

    interface Presenter {
        fun setupUI()
        fun detachView()
    }
}