package com.fcmchat.fcmchat.transactions.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.transactions.interactor.ITransactionInteractor
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@InjectViewState
class TransactionPresenter : AbstractTransactionPresenter() {

    @Inject lateinit var interactor: ITransactionInteractor

    init {
        App.injector.transactionComponent.inject(this)
    }

    override fun addTransactionBtnClick() {
        viewState.showTransactionDialog()
    }
}