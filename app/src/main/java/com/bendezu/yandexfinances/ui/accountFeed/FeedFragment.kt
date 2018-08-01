package com.bendezu.yandexfinances.ui.accountFeed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.data.model.records
import com.bendezu.yandexfinances.ui.addRecord.AddRecordFragment
import com.bendezu.yandexfinances.ui.diagram.DiagramFragment
import com.bendezu.yandexfinances.util.Navigator
import com.bendezu.yandexfinances.util.Screen
import com.bendezu.yandexfinances.util.adapter.AccountPagerAdapter
import com.bendezu.yandexfinances.util.adapter.RecordRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment(), FeedContract.View {

    lateinit var presenter: FeedContract.Presenter
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FeedFragmentPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        navigator = Navigator(fragmentManager)
        accountViewPager.adapter = AccountPagerAdapter(context, presenter)
        val pageListener = object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                (recyclerView.adapter as RecordRecyclerViewAdapter).records =
                        records.filter { it.accountId == position }.toTypedArray()
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
        accountViewPager.addOnPageChangeListener(pageListener)
        accountViewPager.post { pageListener.onPageSelected(accountViewPager.currentItem) }

        fab.setOnClickListener { v ->
            navigator.open(AddRecordFragment.newInstance(fab, root),
                    0,0, R.anim.enter_from_top, R.anim.exit_to_bottom)
        }
        settings.setOnClickListener { v -> navigator.open(Screen.SETTINGS) }

        recyclerView.adapter = RecordRecyclerViewAdapter(records)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun showAccountDiagram(account: Int) {
        navigator.open(DiagramFragment.newInstance(account),
                android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
