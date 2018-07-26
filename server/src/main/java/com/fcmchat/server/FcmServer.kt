package com.fcmchat.server

import io.reactivex.Completable

/**
 * Created by Voronin Igor on 16.07.2018.
 */
interface FcmServer {

    fun sendPushForAll(messageText: String): Completable

    fun sendPushForTo(key: String, messageText: String): Completable

}