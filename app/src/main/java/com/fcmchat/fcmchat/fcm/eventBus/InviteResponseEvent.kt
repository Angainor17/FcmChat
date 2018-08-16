package com.fcmchat.fcmchat.fcm.eventBus

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.fcmchat.fcmchat.fcm.models.InviteResponse
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteResponseEvent : Event() {

    lateinit var slaveUser: UserEntity
    lateinit var chainEntity: ChainEntity
    var result = UserEntity.SEND_AND_INVITE_POLICY

    override fun getKey() = "invite_response"

    override fun setMap(map: Map<String, String>) {
        val inviteReqParams = Gson().fromJson(map[getKey()], InviteResponse::class.java)!!

        slaveUser = inviteReqParams.userSlave
        chainEntity = inviteReqParams.chain
        result = inviteReqParams.result
    }
}