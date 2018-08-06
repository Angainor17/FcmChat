package com.fcmchat.fcmchat.chains.di

import com.fcmchat.fcmchat.chains.data.ChainsRepo
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import com.fcmchat.fcmchat.chains.interactor.ChainsInteractor
import com.fcmchat.fcmchat.chains.interactor.IChainsInteractor
import com.fcmchat.fcmchat.chains.presentation.presenter.ChainsPresenter
import com.fcmchat.fcmchat.chains.presentation.presenter.AbstractChainsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Module
class ChainsModule {

    @Provides fun createPresenter(): AbstractChainsPresenter = ChainsPresenter()

    @Provides fun createRepo(): IChainsRepo = ChainsRepo()

    @Provides fun createInteractor(): IChainsInteractor = ChainsInteractor()

}