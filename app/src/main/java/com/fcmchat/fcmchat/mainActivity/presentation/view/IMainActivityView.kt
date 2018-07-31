package com.fcmchat.fcmchat.mainActivity.presentation.view

import com.arellomobile.mvp.MvpView

/**
 * Created by Voronin Igor on 16.07.2018.
 */
interface IMainActivityView : MvpView {

    fun showText(text: String)

    fun showQrCode(firebaseToken: String)

}