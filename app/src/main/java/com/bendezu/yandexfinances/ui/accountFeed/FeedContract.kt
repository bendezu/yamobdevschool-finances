package com.bendezu.yandexfinances.ui.accountFeed

import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.base.MvpPresenter
import com.bendezu.yandexfinances.ui.base.MvpView

interface FeedContract {

    interface View: MvpView {
        fun showViewPager(primaryCurrencyId: Int, secondaryCurrencyId: Int)
        fun showAccountDiagram(account: Int)
    }

    @FragmentScope
    interface Presenter<in V: View>: MvpPresenter<V> {
        fun setupUI()
        fun onDiagramClicked(account: Int)
    }
}