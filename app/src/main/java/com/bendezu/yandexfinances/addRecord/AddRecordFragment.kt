package com.bendezu.yandexfinances.addRecord

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import com.bendezu.yandexfinances.CategorySpinnerAdapter
import com.bendezu.yandexfinances.CurrencySpinnerAdapter
import com.bendezu.yandexfinances.R
import com.bendezu.yandexfinances.model.categories
import com.bendezu.yandexfinances.model.currencies
import com.bendezu.yandexfinances.util.RevealAnimationSetting
import com.bendezu.yandexfinances.util.registerCircularRevealAnimation
import kotlinx.android.synthetic.main.fragment_add_record.*
import kotlin.math.roundToInt

interface AddRecordFragmentListener {
    fun onCancelClicked()
}

private const val ARG_REVEAL_SETTINGS = "reveal_settings"

class AddRecordFragment : Fragment(), AddRecordContract.View {

    private var listener: AddRecordFragmentListener? = null
    private lateinit var presenter: AddRecordContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddRecordFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement AddRecordFragmentListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AddRecordFragmentPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_record, container, false)
        if (savedInstanceState == null) {
            registerCircularRevealAnimation(context, view, arguments.getParcelable(ARG_REVEAL_SETTINGS),
                    getColor(context, R.color.colorAccent), getColor(context, R.color.colorPrimaryDark))
        }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.setupUI()

        amountEditText.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(amountEditText, InputMethodManager.SHOW_IMPLICIT)
        amountEditText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.onAmountChanged(p0.toString())
            }
        })

        recordTypeToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.onRecordTypeChanged(isChecked)
        }

        toolbar.inflateMenu(R.menu.add_record_menu)
        toolbar.setNavigationOnClickListener { listener?.onCancelClicked() }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    fragmentManager.popBackStack()
                    true
                }
                else -> false
            }
        }

        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        categorySpinner.adapter = CategorySpinnerAdapter(inflater, categories)

        currencySpinner.adapter = CurrencySpinnerAdapter(inflater, currencies)
        currencySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onCurrencyChanged(currencies[position])
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun updateRecordInfo(info: String) {
        recordInfo.text = info
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(fab: View, root: View): AddRecordFragment {
            val centreX = (fab.x + fab.width  / 2).roundToInt()
            val centreY = (fab.y + fab.height / 2).roundToInt()
            val settings = RevealAnimationSetting(centreX, centreY, root.width, root.height)
            return AddRecordFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_REVEAL_SETTINGS, settings)
                }
            }
        }
    }
}
