package com.bendezu.yandexfinances.accountFeed

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.addRecord.AddRecordFragment
import com.bendezu.yandexfinances.util.RevealAnimationSetting
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlin.math.roundToInt

interface FeedFragmentClickListener {
    fun onSettingsClicked()
    fun onAddRecordClicked()
}

const val FEED_FRAGMENT_TAG = "FeedFragment"

class FeedFragment : Fragment(), FeedContract.View {

    private var listener: FeedFragmentClickListener? = null
    lateinit var presenter: FeedContract.Presenter

    private var mainBalance: TextView? = null
    private var secondaryBalance: TextView? = null

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
        accountViewPager.adapter = AccountPagerAdapter(this)
        val pageListener = object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                Toast.makeText(context, "update list for account " + position, Toast.LENGTH_SHORT).show()
            }
        }
        accountViewPager.addOnPageChangeListener(pageListener)
        accountViewPager.post(Runnable { pageListener.onPageSelected(accountViewPager.currentItem) })

        fab.setOnClickListener { v ->
            val centreX = (fab.x + fab.width  / 2).roundToInt()
            val centreY = (fab.y + fab.height / 2).roundToInt()
            val bundle = Bundle()
            bundle.putParcelable("anim", RevealAnimationSetting(centreX, centreY,root.width, root.height))
            val fragment = AddRecordFragment()
            fragment.arguments = bundle

            fragmentManager.beginTransaction()
                    .setCustomAnimations(0,0, R.anim.enter_from_top, R.anim.exit_to_bottom)
                    .replace(R.id.container, fragment)
                    .addToBackStack(null).commit()
            listener?.onAddRecordClicked()
        }
        settings.setOnClickListener { v -> listener?.onSettingsClicked() }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun setPrimaryBalance(balance: String) {
        mainBalance?.text = balance
    }
    override fun setAlternateBalance(balance: String) {
        secondaryBalance?.text = balance
    }

    override fun showAccountDiagram(account: Int) {
        Toast.makeText(context, "Diagram $account", Toast.LENGTH_SHORT).show()
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
