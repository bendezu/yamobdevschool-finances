package com.bendezu.yandexfinances.injection.components.activity

import com.bendezu.yandexfinances.injection.base.ComponentBuilder
import com.bendezu.yandexfinances.injection.base.MyComponent
import com.bendezu.yandexfinances.injection.modules.ActivityModule
import com.bendezu.yandexfinances.injection.modules.FragmentModule
import com.bendezu.yandexfinances.injection.scopes.ActivityScope
import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.addRecord.AddRecordFragment
import com.bendezu.yandexfinances.ui.main.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface MainActivityComponent: MyComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder: ComponentBuilder<MainActivityComponent, ActivityModule>
}