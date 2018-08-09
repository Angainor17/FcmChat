package com.fcmchat.server.model

import com.fcmchat.server.FcmServer

/**
 * Created by Voronin Igor on 26.07.2018.
 */
class FcmMessage(
        val messageText: String,
        val key: String = "",
        val paramName: String = FcmServer.DATA_KEY
)