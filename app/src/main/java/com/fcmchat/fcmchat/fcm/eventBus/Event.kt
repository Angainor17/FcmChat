package com.fcmchat.fcmchat.fcm.eventBus

/**
 * Created by Voronin Igor on 08.08.2018.
 */
interface Event {

    fun setMap(map: Map<String, String>)

    fun isTypeMatch(map: Map<String, String>): Boolean

}