package com.fcmchat.fcmchat.invite.business

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.invite.data.IInviteActivityRepo
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 31.07.2018.
 */
class InviteActivityInteractor : IInviteActivityInteractor {

    @Inject lateinit var repo: IInviteActivityRepo

    init {
        App.injector.inviteComponent.inject(this)
    }

    override fun sendMessageAll(message: String): Completable = repo.sendMessageAll(message)


    override fun sendMessageTo(key: String, message: String): Completable = repo.sendMessageTo(key, message)

    override fun saveFcmKey(key: String) {
        repo.saveFcmKey(key)
    }
}