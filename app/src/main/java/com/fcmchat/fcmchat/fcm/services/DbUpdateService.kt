package com.fcmchat.fcmchat.fcm.services

import android.content.Intent
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.transactions.DataTransaction


class DbUpdateService : DbService("DbUpdateService") {

    override fun onHandleIntent(intent: Intent?) {
        App.injector.appComponent.inject(this)
        super.onHandleIntent(intent)
        val dataTransaction = transactionEvent.transaction as DataTransaction
    }
}
