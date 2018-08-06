package com.fcmchat.fcmchat.transactions.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.transactions.data.ITransactionRepo
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionInteractor : ITransactionInteractor {

    @Inject lateinit var repo: ITransactionRepo

    init {
        App.injector.transactionComponent.inject(this)
    }
}