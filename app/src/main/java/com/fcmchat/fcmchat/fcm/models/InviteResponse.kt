package com.fcmchat.fcmchat.fcm.models

import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteResponse(
        request: InviteRequestEvent,

        val userKey: String,
        val userName: String,
        val chainName: String = request.chainName,
        val result: Int
)