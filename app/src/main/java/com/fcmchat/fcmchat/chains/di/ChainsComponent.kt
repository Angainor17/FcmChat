package com.fcmchat.fcmchat.chains.di

import com.fcmchat.fcmchat.app.di.AppComponent
import com.fcmchat.fcmchat.chains.interactor.ChainsInteractor
import com.fcmchat.fcmchat.chains.presentation.presenter.ChainsPresenter
import com.fcmchat.fcmchat.chains.presentation.view.ChainsFragment
import dagger.Component

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Component(dependencies = [AppComponent::class], modules = [ChainsModule::class])
interface ChainsComponent {

    fun inject(interactor: ChainsInteractor)
    fun inject(presenter: ChainsPresenter)
    fun inject(view: ChainsFragment)

}