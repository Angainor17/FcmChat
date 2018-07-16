package com.fcmchat.fcmchat.fcm

import com.fcmchat.fcmchat.debug
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MyFirebaseInstanceIDService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        debug("onMessageReceived")

        if (remoteMessage != null) {
            if (remoteMessage.data.isNotEmpty()) {
                debug(Gson().toJson(remoteMessage.data))
            }
        }
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        debug("onDeletedMessages")
    }

    override fun onMessageSent(s: String?) {
        super.onMessageSent(s)
        debug("onMessageSent")
    }

    override fun onSendError(s: String?, e: Exception?) {
        super.onSendError(s, e)
        debug("onSendError")
    }
}