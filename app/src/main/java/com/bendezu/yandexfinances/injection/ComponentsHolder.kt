package com.bendezu.yandexfinances.injection

import android.content.Context
import com.bendezu.yandexfinances.injection.base.ComponentBuilder
import com.bendezu.yandexfinances.injection.base.MyComponent
import com.bendezu.yandexfinances.injection.components.ApplicationComponent
import com.bendezu.yandexfinances.injection.components.DaggerApplicationComponent
import com.bendezu.yandexfinances.injection.modules.ApplicationModule
import javax.inject.Inject
import javax.inject.Provider

class ComponentsHolder(private val context: Context) {

    private lateinit var applicationComponent: ApplicationComponent

    @Inject lateinit var builders: MutableMap<Class<*>, Provider<ComponentBuilder<*, *>>>
    private var components: MutableMap<Class<*>, MyComponent<*>?> = HashMap()

    fun init() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(context)).build()
        applicationComponent.injectComponentsHolder(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

    fun getComponent(cls: Class<*>): MyComponent<*> {
        var component: MyComponent<*>? = components[cls]
        if (component == null) {
            val builder: ComponentBuilder<*, *>? = builders[cls]?.get()
            component = builder?.build()
            components[cls] = component
        }
        return component!!
    }

    fun releaseComponent(cls: Class<*>) {
        components[cls] = null
    }
}