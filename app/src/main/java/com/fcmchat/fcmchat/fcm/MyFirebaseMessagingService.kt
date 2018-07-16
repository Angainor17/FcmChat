package com.fcmchat.fcmchat.fcm

import com.fcmchat.fcmchat.debug
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        debug("onMessageReceived")
    }
}
