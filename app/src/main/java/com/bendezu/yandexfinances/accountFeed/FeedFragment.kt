package com.bendezu.yandexfinances.accountFeed

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendezu.yandexfinances.R
import kotlinx.android.synthetic.main.account.*
import kotlinx.android.synthetic.main.fragment_feed.*

interface FeedFragmentClickListener {
    fun onSettingsClicked()
}

class FeedFragment : Fragment(), FeedContract.View {

    private var listener: FeedFragmentClickListener? = null
    private lateinit var presenter: FeedContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FeedFragmentClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FeedFragmentClickListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FeedFragmentPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.setupUI()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Not implemented yet", Snackbar.LENGTH_LONG).show()
        }
        settings.setOnClickListener { view ->
            listener?.onSettingsClicked()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setPrimaryBalance(balance: String) {
        primaryBalance.text = balance
    }
    override fun setAlternateBalance(balance: String) {
        alternateBalance.text = balance
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
