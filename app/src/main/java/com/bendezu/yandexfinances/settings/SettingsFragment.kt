package com.bendezu.yandexfinances.settings

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendezu.yandexfinances.R
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
        back.setOnClickListener { listener?.onBackClicked() }
        showDecimals.setOnClickListener{
            Snackbar.make(it, "Not implemented yet", Snackbar.LENGTH_LONG).show()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
