package com.fcmchat.fcmchat.mainActivity.data

import android.content.SharedPreferences
import com.fcmchat.server.FcmServer
import com.fcmchat.server.FcmServerFactory
import com.fcmchat.server.model.FcmMessage
import io.reactivex.Completable


/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MainActivityRepo : IMainActivityRepo {

    private var fcmServer: FcmServer = FcmServerFactory.createServer()
    private lateinit var sharedPreferences: SharedPreferences

    override fun sendMessageAll(message: String): Completable {
        return fcmServer.sendPushForAll(FcmMessage(message))
    }

    override fun sendMessageTo(key: String, message: String): Completable {
        return fcmServer.sendPushForTo(FcmMessage(message, key))
    }

    override fun saveFcmKey(key: String) {

    }
}