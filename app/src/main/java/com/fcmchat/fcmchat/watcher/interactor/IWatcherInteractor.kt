package com.fcmchat.fcmchat.watcher.interactor

import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import io.reactivex.Completable

/**
 * Created by Voronin Igor on 09.08.2018.
 */
interface IWatcherInteractor {

    fun acceptInvitation(request: InviteRequestEvent): Completable
    fun newUserAddToChain(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean): Completable
    fun transactionEvent(request: TransactionEvent)
}