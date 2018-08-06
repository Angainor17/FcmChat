package com.fcmchat.fcmchat.chains.data

import android.content.Context
import com.fcmchat.fcmchat.app.App
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsRepo : IChainsRepo {

    @Inject lateinit var context: Context

    init {
        App.injector.chainsComponent.inject(this)
    }
}