package com.fcmchat.fcmchat.fcm.models

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity

/**
 * Created by Voronin Igor on 09.08.2018.
 */
class InviteRequestParams(
        var masterEntity: UserEntity,
        var chainEntity: ChainEntity
)