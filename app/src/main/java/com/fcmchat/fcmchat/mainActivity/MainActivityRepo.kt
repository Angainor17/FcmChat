package com.fcmchat.fcmchat.mainActivity

import com.fcmchat.server.FcmServer
import com.fcmchat.server.FcmServerFactory
import com.fcmchat.server.model.FcmMessage
import io.reactivex.Completable


/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MainActivityRepo {

    private var fcmServer: FcmServer? = null

    fun sendMessageAll(message: String): Completable {
        initFcmServer()
        return fcmServer!!.sendPushForAll(FcmMessage(message))
    }

    private fun initFcmServer() {
        if (fcmServer == null) {
            fcmServer = FcmServerFactory.createServer()
        }
    }

    fun sendMessageTo(key: String, message: String): Completable {
        initFcmServer()
        return fcmServer!!.sendPushForTo(FcmMessage(message, key))
    }
}