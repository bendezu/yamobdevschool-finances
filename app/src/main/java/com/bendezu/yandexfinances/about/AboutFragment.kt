package com.bendezu.yandexfinances.about

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendezu.yandexfinances.R
import kotlinx.android.synthetic.main.fragment_about.*

interface AboutFragmentClickListener {
    fun onBackClicked()
}

class AboutFragment : Fragment() {

    private var listener: AboutFragmentClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AboutFragmentClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement AboutFragmentClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        toolbar.setNavigationOnClickListener{ listener?.onBackClicked() }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
