package com.fcmchat.fcmchat.mainActivity

import com.arellomobile.mvp.MvpView

/**
 * Created by Voronin Igor on 16.07.2018.
 */
interface IMainActivityView : MvpView {

    fun showSuccess()

    fun showFail()

    fun showText(text: String)

    fun showQrCode(firebaseToken: String)

}