package com.fcmchat.fcmchat.invite.presentation.view

import com.arellomobile.mvp.MvpView

/**
 * Created by Voronin Igor on 16.07.2018.
 */
interface IInviteActivityView : MvpView {

    fun showText(text: String)

    fun showQrCode(firebaseToken: String)

}