package com.bendezu.yandexfinances.accountFeed

import com.bendezu.yandexfinances.model.AccountRepository
import com.bendezu.yandexfinances.model.ExchangeRepository
import com.bendezu.yandexfinances.model.getAlternateCurrency
import com.bendezu.yandexfinances.model.getPrimaryCurrency

class FeedFragmentPresenter(var view: FeedContract.View?) : FeedContract.Presenter {

    private val accountRepository: AccountRepository = AccountRepository()
    private val exchangeRepository: ExchangeRepository = ExchangeRepository()

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