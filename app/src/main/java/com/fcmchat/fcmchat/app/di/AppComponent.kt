package com.fcmchat.fcmchat.app.di

import android.content.Context
import dagger.Component

/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context

}