package com.fcmchat.fcmchat.router.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.router.presentation.view.INavView

/**
 * Created by Voronin Igor on 03.08.2018.
 */
abstract class INavPresenter : MvpPresenter<INavView>() {

    abstract fun screenBtnClick(key: String)
    abstract fun navigatorCreated()
    abstract fun acceptInvitation(request: InviteRequestEvent)
    abstract fun newUserAddToChain(response: InviteResponseEvent, addFlag: Boolean, inviteFlag: Boolean)

}