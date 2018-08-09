package com.fcmchat.fcmchat.fcm.eventBus

/**
 * Created by Voronin Igor on 08.08.2018.
 */
abstract class Event {

    abstract fun setMap(map: Map<String, String>)
    abstract fun getKey(): String

    fun isTypeMatch(map: Map<String, String>): Boolean {
        map.forEach { entry -> if (entry.key == getKey()) return true }
        return false
    }
}