package com.fcmchat.fcmchat.server.watcher.interactor

import com.fcmchat.server.fcm.eventBus.InviteRequestEvent
import com.fcmchat.server.fcm.eventBus.InviteResponseEvent
import com.fcmchat.server.fcm.eventBus.TransactionEvent
import io.reactivex.Completable

/**
 * Created by Voronin Igor on 09.08.2018.
 */
interface IWatcherInteractor {

    fun acceptInvitation(request: InviteRequestEvent): Completable
    fun newUserAddToChain(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean): Completable
    fun transactionEvent(request: TransactionEvent)
}