package com.bendezu.yandexfinances.ui.main

import android.os.Bundle
import com.bendezu.yandexfinances.App
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.injection.components.activity.MainActivityComponent
import com.bendezu.yandexfinances.ui.base.BaseActivity
import com.bendezu.yandexfinances.util.Navigator
import com.bendezu.yandexfinances.util.Screen
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject @JvmSuppressWildcards lateinit var presenter: MainContract.Presenter<MainContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (App.instance.componentsHolder.getComponent(javaClass) as MainActivityComponent)
                .inject(this)

        setContentView(R.layout.activity_main)

        presenter.attachView(this)

        if (savedInstanceState == null) {
            Navigator(supportFragmentManager).open(Screen.ACCOUNT_FEED)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()

        if (isFinishing) App.instance.componentsHolder.releaseComponent(javaClass)
    }
}
