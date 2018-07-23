package com.fcmchat.fcmchat.mainActivity

import com.fcmchat.fcmchat.debug
import com.fcmchat.server.FcmServer
import com.fcmchat.server.FcmServerFactory


/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MainActivityRepo {

    companion object {
        const val SENDER_ID = "1019413917607"
    }

    private var messageId = 1

    private lateinit var fcmServer: FcmServer

    fun sendMessage(fcmKey: String) {
//        val fm = FirebaseMessaging.getInstance()
//
//        val remoteMessage = RemoteMessage.Builder("$SENDER_ID@gcm.googleapis.com")
//                .setMessageId(Integer.toString(messageId))
//                .addData("my_message", "Hello World")
//                .addData("my_action", "SAY_HELLO")
//                .build()
//
//        fm.send(remoteMessage)
        messageId++
        debug("sendMessage")
    }

    fun sendMessageAll(firebaseToken: String) {
        fcmServer = FcmServerFactory.createServer(firebaseToken)

        fcmServer.sendPushForAll()
    }
}