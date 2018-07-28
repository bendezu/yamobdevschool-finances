package com.bendezu.yandexfinances.accountFeed

interface FeedContract {

    interface View {
        fun setPrimaryBalance(balance: String)
        fun setAlternateBalance(balance: String)
        fun showAccountDiagram(account: Int)
    }

    interface Presenter {
        fun setupUI()
        fun calculateAccountBalance(account: Int)
        fun onDiagramClicked(account: Int)
        fun detachView()
    }
}