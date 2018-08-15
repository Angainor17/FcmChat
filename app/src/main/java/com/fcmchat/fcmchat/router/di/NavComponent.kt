package com.fcmchat.fcmchat.router.di

import com.fcmchat.fcmchat.app.di.AppComponent
import com.fcmchat.fcmchat.router.presentation.presenter.NavPresenter
import com.fcmchat.fcmchat.router.presentation.view.NavActivity
import dagger.Component

/**
 * Created by Voronin Igor on 03.08.2018.
 */
@Component(modules = [NavModule::class], dependencies = [AppComponent::class])
interface NavComponent {

    fun inject(view: NavActivity)
    fun inject(presenter: NavPresenter)

}