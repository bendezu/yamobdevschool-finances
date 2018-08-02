package com.bendezu.yandexfinances.ui.main

import com.bendezu.yandexfinances.injection.scopes.ActivityScope
import com.bendezu.yandexfinances.ui.base.MvpPresenter
import com.bendezu.yandexfinances.ui.base.MvpView

interface MainContract {
    interface View: MvpView {
    }

    @ActivityScope
    interface Presenter<in V: View>: MvpPresenter<V> {
    }
}