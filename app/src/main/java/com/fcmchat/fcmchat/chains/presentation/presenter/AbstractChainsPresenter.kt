package com.fcmchat.fcmchat.chains.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.chains.presentation.view.IChainsView

/**
 * Created by Voronin Igor on 06.08.2018.
 */
abstract class AbstractChainsPresenter : MvpPresenter<IChainsView>() {

    abstract fun addNewChainBtnClick()

    abstract fun alertDialogSuccess(chainName: String)

}