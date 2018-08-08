package com.fcmchat.fcmchat.fcm.models

import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.invite.business.User

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteRequest(user: User, val microChain: Microchain) {

    val name = user.name
    val userKey = user.key

}