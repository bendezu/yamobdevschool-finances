package com.bendezu.yandexfinances.injection.modules

import com.bendezu.yandexfinances.injection.base.MyModule
import com.bendezu.yandexfinances.injection.scopes.FragmentScope
import com.bendezu.yandexfinances.ui.accountFeed.FeedContract
import com.bendezu.yandexfinances.ui.accountFeed.FeedPresenter
import com.bendezu.yandexfinances.ui.addRecord.AddRecordContract
import com.bendezu.yandexfinances.ui.addRecord.AddRecordPresenter
import com.bendezu.yandexfinances.ui.settings.SettingsContract
import com.bendezu.yandexfinances.ui.settings.SettingsPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule: MyModule {

    @Provides
    @FragmentScope
    fun provideSettingsPresenter(presenter: SettingsPresenter<SettingsContract.View>)
            : SettingsContract.Presenter<SettingsContract.View> = presenter

    @Provides
    @FragmentScope
    fun provideAddRecordPresenter(presenter: AddRecordPresenter<AddRecordContract.View>)
            : AddRecordContract.Presenter<AddRecordContract.View> = presenter

    @Provides
    @FragmentScope
    fun provideFeedPresenter(presenter: FeedPresenter<FeedContract.View>)
            : FeedContract.Presenter<FeedContract.View> = presenter
}