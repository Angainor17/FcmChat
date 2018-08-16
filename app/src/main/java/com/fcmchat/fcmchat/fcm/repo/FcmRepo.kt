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
        const val USER_NAME = "fcm_name"
        const val SHARED_PREFERENCES_NAME = "fcm"
    }

    private val fcmServer: FcmServer = FcmServerFactory.createServer()

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun notifyAll(paramName: String, message: String, topicName: String) =
            fcmServer.sendPushAll(FcmMessage(message, paramName = paramName), topicName)

    override fun sendTo(key: String, paramName: String, message: String) = fcmServer.sendPushTo(
            FcmMessage(message, key, paramName)
    )

    override fun getFcmKey(): String {
        var key = sharedPreferences.getString(FCM_KEY, "")
        if (key.isNullOrEmpty() || key == "null") {
            key = FirebaseInstanceId.getInstance().token.toString()
            sharedPreferences.edit().putString(FCM_KEY, key).apply()
        }
        return key!!
    }

    override fun getUserName(): String {
        var userName = sharedPreferences.getString(USER_NAME, "")
        if (userName.isNullOrEmpty() || userName == "null") {
            userName = createUserName()
            sharedPreferences.edit().putString(USER_NAME, userName).apply()
        }
        return userName!!
    }

    private fun createUserName(): String = UUID.randomUUID().toString()

    override fun subscribeToTopic(topicName: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topicName)
    }
}