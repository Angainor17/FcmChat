package com.fcmchat.fcmchat.mainActivity

import com.fcmchat.server.FcmServer
import com.fcmchat.server.FcmServerFactory
import io.reactivex.Completable


/**
 * Created by Voronin Igor on 16.07.2018.
 */
class MainActivityRepo {

    fun sendMessageAll(firebaseToken: String, message: String): Completable {
        initFcmServer()

        return fcmServer!!.sendPushForAll(message)
    }

    private fun initFcmServer() {
        if (fcmServer == null) {
            fcmServer = FcmServerFactory.createServer()
        }
    }

    fun sendMessageTo(key: String, message: String): Completable {
        initFcmServer()
        return fcmServer!!.sendPushForTo(key, message)
    }

    private var fcmServer: FcmServer? = null
}