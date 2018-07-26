package com.fcmchat.server

import com.fcmchat.adminsdk.FirebaseApp
import com.fcmchat.adminsdk.FirebaseOptions
import com.fcmchat.adminsdk.messaging.AndroidConfig
import com.fcmchat.adminsdk.messaging.FirebaseMessaging
import com.fcmchat.adminsdk.messaging.Message
import com.google.auth.oauth2.GoogleCredentials
import io.reactivex.Completable

/**
 * Created by Voronin Igor on 16.07.2018.
 */
internal class FcmServerImpl : FcmServer {

    private var firebaseApp: FirebaseApp

    init {
        val serviceAccount = FcmServerFactory.context.resources.openRawResource(R.raw.fcm_server)

        val googleCredentials = GoogleCredentials.fromStream(serviceAccount)

        val options = FirebaseOptions.Builder()
                .setCredentials(googleCredentials)
                .setDatabaseUrl("https://fcmchat-b191a.firebaseio.com")
                .build()

        firebaseApp = FirebaseApp.initializeApp(options)
    }

    override fun sendPushForTo(key: String, messageText: String): Completable = Completable.create {
        try {
            val message = Message.builder()
                    .putData("message", messageText)
                    .setAndroidConfig(AndroidConfig.builder()
                            .setPriority(AndroidConfig.Priority.HIGH)
                            .build())
                    .setToken(key)
                    .build()

            FirebaseMessaging.getInstance().send(message)
            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    }

    override fun sendPushForAll(messageText: String): Completable {
        return Completable.create {
            try {
                val message = Message.builder()
                        .putData("message", messageText)
                        .setAndroidConfig(AndroidConfig.builder()
                                .setPriority(AndroidConfig.Priority.HIGH)
                                .build())
                        .setTopic("allDevices")
                        .build()

                FirebaseMessaging.getInstance().send(message)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}