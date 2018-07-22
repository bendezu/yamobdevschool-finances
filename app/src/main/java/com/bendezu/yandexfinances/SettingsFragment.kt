package com.bendezu.yandexfinances

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

interface OnFragmentInteractionListener {
    fun onFragmentInteraction()
}

class SettingsFragment : PreferenceFragmentCompat() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences);

        findPreference("about").setOnPreferenceClickListener { listener?.onFragmentInteraction(); true }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
