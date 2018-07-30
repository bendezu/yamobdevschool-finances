package com.bendezu.yandexfinances

import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.bendezu.yandexfinances.about.AboutFragment
import com.bendezu.yandexfinances.accountFeed.FeedFragment
import com.bendezu.yandexfinances.addRecord.AddRecordFragment
import com.bendezu.yandexfinances.diagram.DiagramFragment
import com.bendezu.yandexfinances.settings.SettingsFragment

enum class Screen {
    ACCOUNT_FEED,
    ADD_RECORD,
    DIAGRAM,
    SETTINGS,
    ABOUT
}

class Navigator(private val fragmentManager: FragmentManager) {

    fun open(fragment: Fragment,
                     @AnimatorRes @AnimRes enter: Int, @AnimatorRes @AnimRes exit: Int,
                     @AnimatorRes @AnimRes popEnter: Int, @AnimatorRes @AnimRes popExit: Int) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(enter, exit, popEnter, popExit)
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.toString())
                .commit()
    }

    fun open(screen: Screen) {
        when (screen) {
            Screen.ACCOUNT_FEED -> {
                fragmentManager.beginTransaction()
                        .add(R.id.container, FeedFragment()).commit()
            }
            Screen.ADD_RECORD -> {
                open(AddRecordFragment(),
                        0,0, R.anim.enter_from_top, R.anim.exit_to_bottom)
            }
            Screen.DIAGRAM -> {
                open(DiagramFragment(),
                        R.anim.fade_in, R.anim.fade_out,
                        R.anim.fade_in, R.anim.fade_out)
            }
            Screen.SETTINGS -> {
                open(SettingsFragment(),
                        R.anim.enter_from_top, R.anim.exit_to_bottom,
                        R.anim.enter_from_left, R.anim.exit_to_right)
            }
            Screen.ABOUT -> {
                open(AboutFragment(),
                        R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
            }
        }
    }

    fun back() {
        fragmentManager.popBackStack()
    }

}