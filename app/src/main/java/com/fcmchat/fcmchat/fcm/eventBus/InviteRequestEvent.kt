package com.fcmchat.fcmchat.fcm.eventBus

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteRequestEvent : Event {

    override fun setMap(map: Map<String, String>) {

    }

    override fun isTypeMatch(map: Map<String, String>): Boolean {
        return false
    }
}