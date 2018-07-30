package com.fcmchat.server

import com.fcmchat.server.model.FcmMessage
import io.reactivex.Completable

/**
 * Created by Voronin Igor on 16.07.2018.
 */
interface FcmServer {

    companion object {
        const val ALL_DEVICES_TOPIC = "allDevices"
        const val DATA_KEY = "message"
    }


    fun sendPushForAll(message: FcmMessage): Completable

    fun sendPushForTo(message: FcmMessage): Completable

}