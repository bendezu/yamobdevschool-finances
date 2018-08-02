package com.bendezu.yandexfinances.ui.accountFeed

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendezu.yandexfinances.App
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.data.model.records
import com.bendezu.yandexfinances.injection.components.fragment.FeedFragmentComponent
import com.bendezu.yandexfinances.ui.addRecord.AddRecordFragment
import com.bendezu.yandexfinances.ui.base.BaseFragment
import com.bendezu.yandexfinances.ui.diagram.DiagramFragment
import com.bendezu.yandexfinances.util.Navigator
import com.bendezu.yandexfinances.util.Screen
import com.bendezu.yandexfinances.util.adapter.AccountPagerAdapter
import com.bendezu.yandexfinances.util.adapter.RecordRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment: BaseFragment(), FeedContract.View {

    @Inject @JvmSuppressWildcards lateinit var presenter: FeedContract.Presenter<FeedContract.View>
    lateinit var navigator: Navigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (App.instance.componentsHolder.getComponent(javaClass) as FeedFragmentComponent)
                .inject(this)

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        presenter.setupUI()

        navigator = Navigator(fragmentManager)

        fab.setOnClickListener {
            navigator.open(AddRecordFragment.newInstance(fab, root),
                    0,0, R.anim.enter_from_top, R.anim.exit_to_bottom)
        }
        settings.setOnClickListener { navigator.open(Screen.SETTINGS) }

        recyclerView.adapter = RecordRecyclerViewAdapter(records)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun showViewPager(primaryCurrencyId: Int, secondaryCurrencyId: Int) {
        accountViewPager.adapter = AccountPagerAdapter(context, presenter, primaryCurrencyId, secondaryCurrencyId)
        val pageListener = object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                (recyclerView.adapter as RecordRecyclerViewAdapter).records =
                        records.filter { it.accountId == position }.toTypedArray()
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
        accountViewPager.addOnPageChangeListener(pageListener)
        accountViewPager.post { pageListener.onPageSelected(accountViewPager.currentItem) }
    }

    override fun showAccountDiagram(account: Int) {
        navigator.open(DiagramFragment.newInstance(account),
                android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
        if (isRemoving) App.instance.componentsHolder.releaseComponent(javaClass)
    }
}
