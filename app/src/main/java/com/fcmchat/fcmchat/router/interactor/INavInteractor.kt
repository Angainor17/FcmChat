package com.fcmchat.fcmchat.router.interactor

import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import io.reactivex.Completable

/**
 * Created by Voronin Igor on 09.08.2018.
 */
interface INavInteractor {

    fun acceptInvitation(request: InviteRequestEvent): Completable
    fun newUserAddToChain(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean)
}