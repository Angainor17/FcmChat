package com.fcmchat.server.fcm.eventBus

import com.fcmchat.server.fcm.models.InviteResponse
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteResponseEvent : Event() {

    var chainName = ""
    var userName = ""
    var userKey = ""
    var result = 0

    override fun getKey() = "invite_response"

    override fun setMap(map: Map<String, String>) {
        val inviteReqParams = Gson().fromJson(map[getKey()], InviteResponse::class.java)!!

        chainName = inviteReqParams.chainName
        userName = inviteReqParams.userName
        userKey = inviteReqParams.userKey
        result = inviteReqParams.result
    }
}