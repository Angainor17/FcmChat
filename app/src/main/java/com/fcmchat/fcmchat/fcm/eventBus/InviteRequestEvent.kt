package com.fcmchat.fcmchat.fcm.eventBus

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.fcmchat.fcmchat.fcm.models.InviteRequestParams
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteRequestEvent : Event() {

    lateinit var masterUser: UserEntity
    lateinit var chain: ChainEntity

    override fun getKey() = "invite_request"

    override fun setMap(map: Map<String, String>) {
        val inviteReqParams = Gson().fromJson(map[getKey()], InviteRequestParams::class.java)!!

        masterUser = inviteReqParams.masterEntity
        chain = inviteReqParams.chainEntity
    }
}