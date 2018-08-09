package com.fcmchat.fcmchat.fcm.models

import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.invite.business.User

/**
 * Created by Voronin Igor on 09.08.2018.
 */
class InviteRequestParams(
        user: User,
        microchain: Microchain,

        val userName: String = user.name,
        val chainName: String = microchain.chainName,
        val fcmKey: String = user.key
)