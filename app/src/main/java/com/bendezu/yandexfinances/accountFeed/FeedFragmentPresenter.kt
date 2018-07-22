package com.bendezu.yandexfinances.accountFeed

class FeedFragmentPresenter(var view: FeedContract.View?): FeedContract.Presenter {
    override fun detachView() {
        view = null
    }

    override fun setupUI() {
        view?.setPrimaryBalance("1234,56 RUB")
        view?.setAlternateBalance("12,34 USD")
    }

}