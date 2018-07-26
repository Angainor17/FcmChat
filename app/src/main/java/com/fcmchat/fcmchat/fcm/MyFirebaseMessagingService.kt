package com.fcmchat.fcmchat.fcm

import com.fcmchat.fcmchat.debug
import com.fcmchat.fcmchat.eventBus.NewMessageEvent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage != null && remoteMessage.data.isNotEmpty()) {
            debug(Gson().toJson(remoteMessage.data))
            EventBus.getDefault().post(NewMessageEvent(remoteMessage.data))
        }
    }
}
