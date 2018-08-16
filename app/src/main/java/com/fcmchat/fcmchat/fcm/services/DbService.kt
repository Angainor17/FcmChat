package com.fcmchat.fcmchat.fcm.services

import android.app.IntentService
import android.content.Intent
import android.support.annotation.CallSuper
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.google.gson.Gson

abstract class DbService(name: String) : IntentService(name) {

    lateinit var transactionEvent: TransactionEvent

    @CallSuper
    override fun onHandleIntent(intent: Intent?) {
        transactionEvent = Gson().fromJson(intent?.extras?.get("extra") as String, TransactionEvent::class.java)
    }
}