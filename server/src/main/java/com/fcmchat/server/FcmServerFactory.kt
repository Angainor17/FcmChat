package com.fcmchat.server

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class FcmServerFactory {

    companion object {
        fun init(context: Context) {
            this.context = context
        }

        fun createServer(registrationToken: String): FcmServer = FcmServerImpl(registrationToken)

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}