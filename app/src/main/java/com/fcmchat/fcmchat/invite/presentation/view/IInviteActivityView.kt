package com.fcmchat.fcmchat.invite.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by Voronin Igor on 16.07.2018.
 */

interface IInviteActivityView : MvpView {

    //    @StateStrategyType(AddToEndStrategy::class)
    fun setDeviceId(deviceID: String)

    @StateStrategyType(SkipStrategy::class)
    fun showQrCode(firebaseToken: String)

    @StateStrategyType(SkipStrategy::class)
    fun showAlert(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun setChainsList(list: ArrayList<String>)

}