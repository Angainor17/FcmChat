package com.fcmchat.fcmchat.transactions.data

import android.content.Context
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionRepo : ITransactionRepo {

    @Inject lateinit var context: Context

    init {
        App.injector.transactionComponent.inject(this)
    }
}