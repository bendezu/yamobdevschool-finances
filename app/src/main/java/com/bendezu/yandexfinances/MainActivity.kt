package com.bendezu.yandexfinances

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.account.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Not implemented yet", Snackbar.LENGTH_LONG).show()
        }
        settings.setOnClickListener { view ->
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        primaryBalance.text = "1234,56 RUB"
        alternateBalance.text = "12,34 USD"
    }
}
