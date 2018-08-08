package com.fcmchat.fcmchat.fcm.repo

import android.content.Context
import android.content.SharedPreferences
import com.fcmchat.server.FcmServer
import com.fcmchat.server.FcmServerFactory
import com.fcmchat.server.model.FcmMessage
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*

/**
 * Created by Voronin Igor on 07.08.2018.
 */
class FcmRepo(context: Context) : IFcmRepo {

    companion object {
        const val FCM_KEY = "fcm_key"
        const val USER_NAME = "fcm_key"
    }

    private val fcmServer: FcmServer = FcmServerFactory.createServer()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("fcm", Context.MODE_PRIVATE)

    override fun notifyAll(message: String, topicName: String) = fcmServer.sendPushAll(FcmMessage(message), topicName)

    override fun sendTo(key: String, message: String) = fcmServer.sendPushTo(FcmMessage(message, key))

    override fun getFcmKey(): String {
        var key = sharedPreferences.getString(FCM_KEY, "")
        if (key.isEmpty()) {
            key = FirebaseInstanceId.getInstance().token.toString()
            sharedPreferences.edit().putString(FCM_KEY, key).apply()
        }
        return key
    }

    override fun getUserName(): String {
        var userName = sharedPreferences.getString(USER_NAME, "")
        if (userName.isEmpty()) {
            userName = createUserName()
            sharedPreferences.edit().putString(USER_NAME, userName).apply()
        }
        return userName
    }

    private fun createUserName(): String = UUID.randomUUID().toString()

    override fun subscribeToTopic(topicName: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topicName)
    }
}