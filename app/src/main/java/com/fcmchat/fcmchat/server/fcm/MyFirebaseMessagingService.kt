package com.fcmchat.server.fcm

import com.fcmchat.server.fcm.eventBus.EventFactory
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.greenrobot.eventbus.EventBus

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage != null && remoteMessage.data.isNotEmpty()) {
            EventBus.getDefault().post(EventFactory.createMessageEvent(remoteMessage.data))
        }
    }
}
