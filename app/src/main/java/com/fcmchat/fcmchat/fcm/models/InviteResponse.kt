package com.fcmchat.fcmchat.fcm.models

import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteResponse(
        request: InviteRequestEvent,

        lateinit var userKey: UserEntity

//        val userKey: String,

        val chainName: String = request.chainName,
        val result: Int
)