package com.fcmchat.server

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class FcmServerFactory {

    companion object {
        fun create(key: String): FcmServer = FcmServerImpl(key)
    }

}