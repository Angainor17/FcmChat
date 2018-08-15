package com.fcmchat.server.fcm.eventBus

import com.fcmchat.server.fcm.models.InviteRequestParams
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteRequestEvent : Event() {

    var chainName = ""
    var userName = ""
    var userKey = ""

    override fun getKey() = "invite_request"

    override fun setMap(map: Map<String, String>) {
        val inviteReqParams = Gson().fromJson(map[getKey()], InviteRequestParams::class.java)!!

//        chainName = inviteReqParams.chainName
        userName = inviteReqParams.userName
        userKey = inviteReqParams.fcmKey
    }
}