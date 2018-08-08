package com.fcmchat.fcmchat.invite.di

import com.fcmchat.fcmchat.invite.business.IInviteInteractor
import com.fcmchat.fcmchat.invite.business.InviteInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Module()
class InviteModule {

    @Provides fun getInteractor(): IInviteInteractor = InviteInteractor()
}