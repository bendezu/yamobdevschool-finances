package com.bendezu.yandexfinances

import android.app.Application
import com.bendezu.yandexfinances.injection.ComponentsHolder
import timber.log.Timber

class App: Application() {

    lateinit var componentsHolder: ComponentsHolder
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        componentsHolder = ComponentsHolder(this)
        componentsHolder.init()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}