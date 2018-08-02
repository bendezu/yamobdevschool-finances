package com.bendezu.yandexfinances.injection.components.fragment

import com.bendezu.yandexfinances.injection.base.ComponentBuilder
import com.bendezu.yandexfinances.injection.base.MyComponent
import com.bendezu.yandexfinances.injection.modules.FragmentModule
import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.accountFeed.FeedFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FeedFragmentComponent: MyComponent<FeedFragment> {

    @Subcomponent.Builder
    interface Builder: ComponentBuilder<FeedFragmentComponent, FragmentModule>
}