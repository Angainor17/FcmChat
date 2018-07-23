package com.fcmchat.server

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


/**
 * Created by Voronin Igor on 16.07.2018.
 */
internal class FcmServerImpl(private val registrationToken: String) : FcmServer {

    private var firebaseApp: FirebaseApp

    init {
        val serviceAccount = FileInputStream("path/to/serviceAccountKey.json")
        val googleCredentials = GoogleCredentials.fromStream(serviceAccount)


        val options = FirebaseOptions.Builder()
                .setCredentials(googleCredentials)
                .setDatabaseUrl("https://fcmchat-b191a.firebaseio.com")
                .build()

        firebaseApp = FirebaseApp.initializeApp(options)
    }

    override fun sendPushForAll() {
        val message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build()

        val response = FirebaseMessaging.getInstance().send(message)
    }
}