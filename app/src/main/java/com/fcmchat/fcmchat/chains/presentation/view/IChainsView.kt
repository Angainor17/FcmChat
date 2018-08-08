package com.fcmchat.fcmchat.chains.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.fcmchat.fcmchat.chains.interactor.Microchain

/**
 * Created by Voronin Igor on 06.08.2018.
 */

interface IChainsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showNewChainDialog()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun refreshList(newItems: ArrayList<Microchain>)

}