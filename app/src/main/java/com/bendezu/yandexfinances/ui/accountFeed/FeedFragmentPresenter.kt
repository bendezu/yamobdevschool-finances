package com.bendezu.yandexfinances.ui.accountFeed

class FeedFragmentPresenter(var view: FeedContract.View?) : FeedContract.Presenter {

    override fun detachView() {
        view = null
    }

    override fun onDiagramClicked(account: Int) {
        view?.showAccountDiagram(account)
    }

}