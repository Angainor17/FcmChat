package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsInteractor : IChainsInteractor {

    @Inject lateinit var repo: IChainsRepo

    init {
        App.injector.chainsComponent.inject(this)
    }

}