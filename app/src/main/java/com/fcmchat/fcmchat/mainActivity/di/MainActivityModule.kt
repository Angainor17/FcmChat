package com.fcmchat.fcmchat.mainActivity.di

import com.fcmchat.fcmchat.mainActivity.business.IMainActivityInteractor
import com.fcmchat.fcmchat.mainActivity.business.MainActivityInteractor
import com.fcmchat.fcmchat.mainActivity.data.IMainActivityRepo
import com.fcmchat.fcmchat.mainActivity.data.MainActivityRepo
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Module()
class MainActivityModule {

    @Provides fun getInteractor(): IMainActivityInteractor = MainActivityInteractor()

    @Provides fun getRepo(): IMainActivityRepo = MainActivityRepo()
}