package com.bendezu.yandexfinances.ui.settings

import com.bendezu.yandexfinances.data.local.PreferencesHelper
import com.bendezu.yandexfinances.ui.base.BasePresenter
import javax.inject.Inject

class SettingsPresenter<V: SettingsContract.View> @Inject constructor(private val preferencesHelper: PreferencesHelper)
    : BasePresenter<V>(), SettingsContract.Presenter<V> {

    override fun setupUI() {
        view?.selectPrimaryCurrency(preferencesHelper.getPrimaryCurrency())
        view?.selectAlternateCurrency(preferencesHelper.getAlternateCurrency())
    }

    override fun savePrimaryCurrency(currencyId: Int) {
       preferencesHelper.setPrimaryCurrency(currencyId)
    }

    override fun saveAlternateCurrency(currencyId: Int) {
        preferencesHelper.setSecondaryCurrency(currencyId)
    }
}