package com.fcmchat.server.fcm

import com.fcmchat.server.debugServer
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MyFirebaseInstanceIDService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        debugServer("onMessageReceived")
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        debugServer("onDeletedMessages")
    }

    override fun onMessageSent(s: String?) {
        super.onMessageSent(s)
        debugServer("onMessageSent")
    }

    override fun onSendError(s: String?, e: Exception?) {
        super.onSendError(s, e)
        debugServer("onSendError")
    }
}
