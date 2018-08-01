package com.bendezu.yandexfinances.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.util.Navigator
import com.bendezu.yandexfinances.util.Screen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            Navigator(supportFragmentManager).open(Screen.ACCOUNT_FEED)
        }
    }

}
