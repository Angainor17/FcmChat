package com.fcmchat.fcmchat.fcm.services

import android.content.Intent
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.repo.IUsersRepo
import com.fcmchat.fcmchat.fcm.transactions.InitTransaction
import javax.inject.Inject


class InitDbService : DbService("InitDbService") {

    @Inject lateinit var userRepo: IUsersRepo

    override fun onHandleIntent(intent: Intent?) {
        App.injector.appComponent.inject(this)
        super.onHandleIntent(intent)
        val dataTransaction = transactionEvent.transaction as InitTransaction
    }
}
