package com.fcmchat.fcmchat.invite.di

import com.fcmchat.fcmchat.app.di.AppComponent
import com.fcmchat.fcmchat.invite.business.InviteActivityInteractor
import com.fcmchat.fcmchat.invite.data.InviteActivityRepo
import com.fcmchat.fcmchat.invite.presentation.presenter.InviteActivityPresenter
import dagger.Component

/**
 * Created by Voronin Igor on 31.07.2018.
 */

@Component(dependencies = [AppComponent::class], modules = [InviteModule::class])
interface InviteComponent {

    fun inject(presenter: InviteActivityPresenter)
    fun inject(interactor: InviteActivityInteractor)
    fun inject(repo: InviteActivityRepo)

}