package com.fcmchat.fcmchat.fcm.services

import android.content.Intent
import com.fcmchat.fcmchat.fcm.eventBus.EventFactory
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.fcm.transactions.DataTransaction
import com.fcmchat.fcmchat.fcm.transactions.InitTransaction
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        EventBus.getDefault().register(this)
        if (remoteMessage != null && remoteMessage.data.isNotEmpty()) {
            EventBus.getDefault().post(EventFactory.createMessageEvent(remoteMessage.data))
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    private fun transactionProcess(transactionEvent: TransactionEvent) =
            startService(when (transactionEvent.transaction) {
                is DataTransaction ->
                    Intent(this, DbUpdateService::class.java).setExtra(transactionEvent)
                is InitTransaction ->
                    Intent(this, InitDbService::class.java).setExtra(transactionEvent)
                else -> Intent()
            })

    private fun Intent.setExtra(transactionEvent: TransactionEvent): Intent {
        this.putExtra("extra", Gson().toJson(transactionEvent))
        return this
    }
}



