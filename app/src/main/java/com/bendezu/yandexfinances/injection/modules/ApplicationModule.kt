package com.bendezu.yandexfinances.injection.modules

import android.content.Context
import com.bendezu.yandexfinances.data.local.PreferencesHelper
import com.bendezu.yandexfinances.data.local.PreferencesHelperImp
import com.bendezu.yandexfinances.injection.base.ComponentBuilder
import com.bendezu.yandexfinances.injection.components.ApplicationComponent
import com.bendezu.yandexfinances.injection.components.activity.MainActivityComponent
import com.bendezu.yandexfinances.injection.components.fragment.AddRecordFragmentComponent
import com.bendezu.yandexfinances.injection.components.fragment.FeedFragmentComponent
import com.bendezu.yandexfinances.injection.components.fragment.SettingsFragmentComponent
import com.bendezu.yandexfinances.ui.accountFeed.FeedFragment
import com.bendezu.yandexfinances.ui.accountFeed.FeedPresenter
import com.bendezu.yandexfinances.ui.addRecord.AddRecordFragment
import com.bendezu.yandexfinances.ui.main.MainActivity
import com.bendezu.yandexfinances.ui.settings.SettingsFragment
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(subcomponents = [ MainActivityComponent::class, AddRecordFragmentComponent::class,
    FeedFragmentComponent::class, SettingsFragmentComponent::class ])
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun providesPreferencesHelper(preferencesHelper: PreferencesHelperImp): PreferencesHelper = preferencesHelper
//
//    @Provides
//    @Singleton
//    fun provideCurrencyApi(): CurrencyApi = CurrencyApi.Creator.createSlaApi(cacheFile)
//
//    @Provides
//    @Singleton
//    fun provideDatabaseHelper(databaseHelper: FakeDatabaseHelperImp): DatabaseHelper = databaseHelper
//
//    @Provides
//    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImp()

    @Provides
    @IntoMap
    @ClassKey(MainActivity::class)
    fun provideMainActivityBuilder(builder: MainActivityComponent.Builder)
            : ComponentBuilder<*, *> = builder

    @Provides
    @IntoMap
    @ClassKey(FeedFragment::class)
    fun provideFeedFragmentBuilder(builder: FeedFragmentComponent.Builder)
            : ComponentBuilder<*, *> = builder

    @Provides
    @IntoMap
    @ClassKey(AddRecordFragment::class)
    fun provideAddRecordFragment(builder: AddRecordFragmentComponent.Builder)
            : ComponentBuilder<*, *> = builder

    @Provides
    @IntoMap
    @ClassKey(SettingsFragment::class)
    fun provideSettingsFragmentBuilder(builder: SettingsFragmentComponent.Builder)
            : ComponentBuilder<*, *> = builder
}