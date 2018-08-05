package com.bendezu.yandexfinances.injection.components

import com.bendezu.yandexfinances.injection.ComponentsHolder
import com.bendezu.yandexfinances.injection.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun injectComponentsHolder(componentsHolder: ComponentsHolder)
}