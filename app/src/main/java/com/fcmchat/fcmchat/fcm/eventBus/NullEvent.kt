package com.fcmchat.fcmchat.fcm.eventBus

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class NullEvent : Event {

    private var map: Map<String, String> = HashMap()

    override fun setMap(map: Map<String, String>) {
        this.map = map
    }

    override fun isTypeMatch(map: Map<String, String>) = false

}