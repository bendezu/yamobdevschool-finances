package com.bendezu.yandexfinances.accountFeed

import com.bendezu.yandexfinances.model.AccountRepository
import com.bendezu.yandexfinances.model.ExchangeRepository
import com.bendezu.yandexfinances.model.getAlternateCurrency
import com.bendezu.yandexfinances.model.getPrimaryCurrency

class FeedFragmentPresenter: FeedContract.Presenter {

    var view: FeedContract.View?
    private val accountRepository: AccountRepository
    private val exchangeRepository: ExchangeRepository

    constructor(view: FeedContract.View) {
        this.view = view
        accountRepository = AccountRepository()
        exchangeRepository = ExchangeRepository()
    }

    override fun detachView() {
        view = null
    }

    override fun setupUI() {
        val usdBalance = accountRepository.getUsdBalance()
        val rateKoef = exchangeRepository.getRateCoef(getPrimaryCurrency())
        view?.setPrimaryBalance(usdBalance.multiply(rateKoef).toPlainString() + " " + getPrimaryCurrency())
        view?.setAlternateBalance(usdBalance.toPlainString() + " " + getAlternateCurrency())
    }

}