package com.fcmchat.server.fcm.eventBus

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class NullEvent : Event() {

    private var map: Map<String, String> = HashMap()

    override fun getKey() = ""

    override fun setMap(map: Map<String, String>) {
        this.map = map
    }
}