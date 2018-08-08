package com.fcmchat.server

import com.fcmchat.adminsdk.FirebaseApp
import com.fcmchat.adminsdk.FirebaseOptions
import com.fcmchat.adminsdk.messaging.AndroidConfig
import com.fcmchat.adminsdk.messaging.FirebaseMessaging
import com.fcmchat.adminsdk.messaging.Message
import com.fcmchat.server.model.FcmMessage
import com.google.auth.oauth2.GoogleCredentials
import io.reactivex.Completable

/**
 * Created by Voronin Igor on 16.07.2018.
 */
internal class FcmServerImpl : FcmServer {

    companion object {
        const val DATA_BASE_URL = "https://fcmchat-b191a.firebaseio.com"
    }

    private var firebaseApp: FirebaseApp

    init {
        val serviceAccount = FcmServerFactory.context.resources.openRawResource(R.raw.fcm_server)
        val googleCredentials = GoogleCredentials.fromStream(serviceAccount)

        val options = FirebaseOptions.Builder()
                .setCredentials(googleCredentials)
                .setDatabaseUrl(DATA_BASE_URL)
                .build()

        firebaseApp = FirebaseApp.initializeApp(options)
    }

    override fun sendPushTo(message: FcmMessage): Completable = Completable.create {
        try {
            FirebaseMessaging.getInstance().send(Message.builder()
                    .putData(FcmServer.DATA_KEY, message.messageText)
                    .setAndroidConfig(AndroidConfig.builder()
                            .setPriority(AndroidConfig.Priority.HIGH)
                            .build())
                    .setToken(message.key)
                    .build())
            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    }

    override fun sendPushAll(message: FcmMessage, topicName: String): Completable = Completable.create {
        try {
            FirebaseMessaging.getInstance().send(Message.builder()
                    .putData(FcmServer.DATA_KEY, message.messageText)
                    .setAndroidConfig(AndroidConfig.builder()
                            .setPriority(AndroidConfig.Priority.HIGH)
                            .build())
                    .setTopic(topicName)
                    .build())
            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    }
}