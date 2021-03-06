package com.bendezu.yandexfinances.ui.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.data.model.currencies
import com.bendezu.yandexfinances.util.Navigator
import com.bendezu.yandexfinances.util.Screen
import com.bendezu.yandexfinances.util.adapter.CurrencySpinnerAdapter
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), SettingsContract.View  {

    private lateinit var presenter: SettingsContract.Presenter
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = context.getSharedPreferences("", MODE_PRIVATE)
        presenter = SettingsFragmentPresenter(this, preferences)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        navigator = Navigator(fragmentManager)

        about.setOnClickListener{ navigator.open(Screen.ABOUT) }
        toolbar.setNavigationOnClickListener { navigator.back() }

        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        primaryCurrencySpinner.adapter = CurrencySpinnerAdapter(inflater, currencies)
        primaryCurrencySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.savePrimaryCurrency(position)
            }

        }
        alternateCurrencySpinner.adapter = CurrencySpinnerAdapter(inflater, currencies)
        alternateCurrencySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.saveAlternateCurrency(position)
            }

        }

        presenter.setupUI()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun selectPrimaryCurrency(currencyId: Int) {
        primaryCurrencySpinner.setSelection(currencyId)
    }

    override fun selectAlternateCurrency(currencyId: Int) {
        alternateCurrencySpinner.setSelection(currencyId)
    }

}
