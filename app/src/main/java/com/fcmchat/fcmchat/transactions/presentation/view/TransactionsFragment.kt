package com.fcmchat.fcmchat.transactions.presentation.view

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.transactions.interactor.TransactionItem
import com.fcmchat.fcmchat.transactions.presentation.presenter.AbstractTransactionPresenter
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionsFragment : MvpAppCompatDialogFragment(), ITransactionView {

    @InjectPresenter lateinit var presenter: AbstractTransactionPresenter
    @ProvidePresenter fun createPresenter(): AbstractTransactionPresenter = daggerPresenter
    @Inject lateinit var daggerPresenter: AbstractTransactionPresenter

    @Nullable @BindView(R.id.transactions_list) lateinit var transactionsList: RecyclerView
    @Nullable @BindView(R.id.chains_list_spinner) lateinit var chainsSpinner: Spinner
    @Nullable @OnClick(R.id.add_transaction) fun addTransactionBtnClick() =
            presenter.addTransactionBtnClick()

    private var adapter: TransactionsListAdapter? = null

    override fun showTransactionDialog() {
        TODO("impl")
    }

    override fun setChainsList(list: ArrayList<String>) {
        val spinnerAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, list)!!
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        chainsSpinner.adapter = spinnerAdapter
        chainsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.chainChange(chainsSpinner.selectedItem.toString())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        presenter.viewCreated(chainsSpinner.selectedItem?.toString() ?: "")
    }

    override fun setTransactionsList(list: ArrayList<TransactionItem>) {
        adapter = TransactionsListAdapter(list)
        transactionsList.adapter = adapter
        transactionsList.layoutManager = LinearLayoutManager(context)
        transactionsList.itemAnimator = DefaultItemAnimator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.injector.transactionComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            layoutInflater.inflate(R.layout.transaction_fragment_layout, container, false)
}