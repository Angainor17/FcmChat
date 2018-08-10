package com.fcmchat.fcmchat.fcm.eventBus

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class EventFactory {
    companion object {
        fun createMessageEvent(map: Map<String, String>): Event {
            val events = arrayOf(
                    TransactionEvent(),
                    InviteRequestEvent(),
                    InviteResponseEvent())

            events.forEach {
                if (it.isTypeMatch(map)) {
                    it.setMap(map)
                    return it
                }
            }
            return NullEvent()
        }
    }
}