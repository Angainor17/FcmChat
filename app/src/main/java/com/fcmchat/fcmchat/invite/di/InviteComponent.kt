package com.fcmchat.fcmchat.invite.di

import com.fcmchat.fcmchat.app.di.AppComponent
import com.fcmchat.fcmchat.invite.business.InviteInteractor
import com.fcmchat.fcmchat.invite.presentation.presenter.InvitePresenter
import dagger.Component

/**
 * Created by Voronin Igor on 31.07.2018.
 */

@Component(dependencies = [AppComponent::class], modules = [InviteModule::class])
interface InviteComponent {

    fun inject(presenter: InvitePresenter)
    fun inject(interactor: InviteInteractor)

}