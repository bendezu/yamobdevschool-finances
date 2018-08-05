package com.bendezu.yandexfinances.injection.modules

import com.bendezu.yandexfinances.injection.base.MyModule
import com.bendezu.yandexfinances.injection.scopes.ActivityScope
import com.bendezu.yandexfinances.ui.main.MainContract
import com.bendezu.yandexfinances.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule: MyModule {
    @Provides
    @ActivityScope
    fun provideMainPresenter(presenter: MainPresenter<MainContract.View>)
            : MainContract.Presenter<MainContract.View> = presenter
}