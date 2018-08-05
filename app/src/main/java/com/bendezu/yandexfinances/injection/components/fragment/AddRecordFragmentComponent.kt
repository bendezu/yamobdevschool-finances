package com.bendezu.yandexfinances.injection.components.fragment

import com.bendezu.yandexfinances.injection.base.ComponentBuilder
import com.bendezu.yandexfinances.injection.base.MyComponent
import com.bendezu.yandexfinances.injection.modules.FragmentModule
import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.addRecord.AddRecordFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [(FragmentModule::class)])
interface AddRecordFragmentComponent: MyComponent<AddRecordFragment> {

    @Subcomponent.Builder
    interface Builder: ComponentBuilder<AddRecordFragmentComponent, FragmentModule>
}