package com.fcmchat.fcmchat.server.watcher.presenter

import com.fcmchat.server.fcm.eventBus.InviteRequestEvent
import com.fcmchat.server.fcm.eventBus.InviteResponseEvent
import com.fcmchat.server.fcm.eventBus.TransactionEvent

/**
 * Created by Voronin Igor on 10.08.2018.
 */
interface IWatcherPresenter {

    fun transactionEvent(request: TransactionEvent)
    fun acceptInvitation(request: InviteRequestEvent)
    fun newUserAddToChain(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean)

    fun onDestroy()

}