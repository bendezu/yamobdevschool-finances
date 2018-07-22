package com.bendezu.yandexfinances

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SettingsActivity : AppCompatActivity(), OnFragmentInteractionListener {
    override fun onFragmentInteraction() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, AboutFragment(), ABOUT_FRAGMENT_TAG)
                .addToBackStack(null).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(R.id.container,SettingsFragment()).commit()
    }
}
