package com.fcmchat.fcmchat.fcm.models

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteResponse(
        var userSlave: UserEntity,
        var chain: ChainEntity,

        val result: Int
)