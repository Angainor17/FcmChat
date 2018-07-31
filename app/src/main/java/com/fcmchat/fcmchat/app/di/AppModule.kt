package com.fcmchat.fcmchat.app.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Module()
class AppModule(private val context: Context) {

    @Provides fun context() = context

}