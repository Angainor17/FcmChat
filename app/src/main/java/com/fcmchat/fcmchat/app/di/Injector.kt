package com.fcmchat.fcmchat.app.di

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.invite.di.DaggerInviteComponent
import com.fcmchat.fcmchat.invite.di.InviteModule
import com.fcmchat.fcmchat.invite.di.InviteComponent
import com.fcmchat.fcmchat.router.di.DaggerNavComponent
import com.fcmchat.fcmchat.router.di.NavComponent
import com.fcmchat.fcmchat.router.di.NavModule

/**
 * Created by Voronin Igor on 31.07.2018.
 */
class Injector(app: App) {

    private val appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(app)).build()

    val inviteComponent: InviteComponent = DaggerInviteComponent.builder()
            .inviteModule(InviteModule())
            .appComponent(appComponent)
            .build()

    val navComponent: NavComponent = DaggerNavComponent.builder()
            .navModule(NavModule())
            .appComponent(appComponent)
            .build()

}