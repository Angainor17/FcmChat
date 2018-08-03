package com.fcmchat.fcmchat.invite.di

import com.fcmchat.fcmchat.invite.business.IInviteActivityInteractor
import com.fcmchat.fcmchat.invite.business.InviteActivityInteractor
import com.fcmchat.fcmchat.invite.data.IInviteActivityRepo
import com.fcmchat.fcmchat.invite.data.InviteActivityRepo
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Module()
class InviteModule {

    @Provides fun getInteractor(): IInviteActivityInteractor = InviteActivityInteractor()

    @Provides fun getRepo(): IInviteActivityRepo = InviteActivityRepo()
}