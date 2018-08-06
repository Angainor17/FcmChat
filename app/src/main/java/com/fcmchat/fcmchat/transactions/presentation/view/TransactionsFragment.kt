package com.fcmchat.fcmchat.transactions.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.transactions.presentation.presenter.AbstractTransactionPresenter
import com.fcmchat.fcmchat.transactions.presentation.presenter.TransactionPresenter
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionsFragment : MvpAppCompatDialogFragment(), ITransactionView {

    @InjectPresenter lateinit var presenter: AbstractTransactionPresenter
    @ProvidePresenter fun createPresenter(): AbstractTransactionPresenter = TransactionPresenter()
    @Inject lateinit var daggerPresenter: AbstractTransactionPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.injector.transactionComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            layoutInflater.inflate(R.layout.transaction_fragment_layout, container, false)
}