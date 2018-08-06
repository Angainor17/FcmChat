package com.fcmchat.fcmchat.chains.presentation.presenter

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.IChainsInteractor
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsPresenter : AbstractChainsPresenter() {

    @Inject lateinit var interactor: IChainsInteractor

    init {
        App.injector.chainsComponent.inject(this)
    }
}