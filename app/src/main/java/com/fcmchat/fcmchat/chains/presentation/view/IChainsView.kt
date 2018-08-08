package com.fcmchat.fcmchat.chains.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.fcmchat.fcmchat.chains.interactor.Microchain

/**
 * Created by Voronin Igor on 06.08.2018.
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface IChainsView : MvpView {

    fun showNewChainDialog()

    fun refreshList(newItems: ArrayList<Microchain>)

}