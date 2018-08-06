package com.fcmchat.fcmchat.invite.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by Voronin Igor on 16.07.2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface IInviteActivityView : MvpView {

    fun showText(text: String)

    fun showQrCode(firebaseToken: String)

}