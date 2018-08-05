package com.bendezu.yandexfinances.ui.main

import com.bendezu.yandexfinances.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter<V: MainContract.View> @Inject constructor()
    : BasePresenter<V>(), MainContract.Presenter<V> {


}