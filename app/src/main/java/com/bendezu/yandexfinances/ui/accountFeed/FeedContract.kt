package com.bendezu.yandexfinances.ui.accountFeed

interface FeedContract {

    interface View {
        fun showAccountDiagram(account: Int)
    }

    interface Presenter {
        fun onDiagramClicked(account: Int)
        fun detachView()
    }
}