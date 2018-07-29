package com.bendezu.yandexfinances.accountFeed

import com.bendezu.yandexfinances.model.*

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

    override fun calculateAccountBalance(account: Int) {
        val balance = calculateBalanceInUsd(records.asList(), account)
        val rateKoef = exchangeRepository.getRateCoef(getPrimaryCurrency())
        view?.setPrimaryBalance(balance.multiply(rateKoef).toPlainString() + " " + getPrimaryCurrency().symbol)
        view?.setAlternateBalance(balance.toPlainString() + " " + getAlternateCurrency().symbol)
    }

    override fun onDiagramClicked(account: Int) {
        view?.showAccountDiagram(account)
    }

}