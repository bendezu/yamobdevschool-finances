package com.bendezu.yandexfinances.ui.accountFeed

import com.bendezu.yandexfinances.data.local.PreferencesHelper
import com.bendezu.yandexfinances.ui.base.BasePresenter
import javax.inject.Inject

class FeedPresenter<V: FeedContract.View> @Inject constructor(private val preferencesHelper: PreferencesHelper)
    : BasePresenter<V>(), FeedContract.Presenter<V> {

    override fun setupUI() {
        view?.showViewPager(preferencesHelper.getPrimaryCurrency(), preferencesHelper.getAlternateCurrency())
    }

    override fun onDiagramClicked(account: Int) {
        view?.showAccountDiagram(account)
    }
}