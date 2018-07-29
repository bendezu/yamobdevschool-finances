package com.bendezu.yandexfinances.settings

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendezu.yandexfinances.CurrencySpinnerAdapter
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.model.currencies
import kotlinx.android.synthetic.main.fragment_settings.*

interface SettingsFragmentClickListener {
    fun onAboutClicked()
    fun onBackClicked()
}

class SettingsFragment : Fragment() {

    private var listener: SettingsFragmentClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SettingsFragmentClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SettingsFragmentClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        about.setOnClickListener{ listener?.onAboutClicked() }
        toolbar.setNavigationOnClickListener { listener?.onBackClicked() }

        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        primaryCurrencySpinner.adapter = CurrencySpinnerAdapter(inflater, currencies)
        alternateCurrencySpinner.adapter = CurrencySpinnerAdapter(inflater, currencies)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
