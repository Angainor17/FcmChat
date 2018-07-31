package com.fcmchat.fcmchat.mainActivity.di

import com.fcmchat.fcmchat.app.di.AppComponent
import com.fcmchat.fcmchat.mainActivity.business.MainActivityInteractor
import com.fcmchat.fcmchat.mainActivity.data.MainActivityRepo
import com.fcmchat.fcmchat.mainActivity.presentation.presenter.MainActivityPresenter
import dagger.Component

/**
 * Created by Voronin Igor on 31.07.2018.
 */

@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(presenter: MainActivityPresenter)
    fun inject(interactor: MainActivityInteractor)
    fun inject(repo: MainActivityRepo)

}