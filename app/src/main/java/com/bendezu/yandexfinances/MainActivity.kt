package com.bendezu.yandexfinances

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bendezu.yandexfinances.about.AboutFragment
import com.bendezu.yandexfinances.about.AboutFragmentClickListener
import com.bendezu.yandexfinances.accountFeed.FeedFragment
import com.bendezu.yandexfinances.accountFeed.FeedFragmentClickListener
import com.bendezu.yandexfinances.settings.SettingsFragment
import com.bendezu.yandexfinances.settings.SettingsFragmentClickListener

class MainActivity : AppCompatActivity(), FeedFragmentClickListener, SettingsFragmentClickListener, AboutFragmentClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.main_container, FeedFragment()).commit()
        }
    }

    override fun onSettingsClicked() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.main_container, SettingsFragment())
                .addToBackStack(null).commit()
    }

    override fun onAboutClicked() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.main_container, AboutFragment())
                .addToBackStack(null).commit()
    }
    override fun onBackClicked() {
        supportFragmentManager.popBackStack()
    }
}
