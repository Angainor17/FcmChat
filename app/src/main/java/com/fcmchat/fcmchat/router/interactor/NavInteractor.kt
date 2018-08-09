package com.fcmchat.fcmchat.router.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.fcm.models.InviteResponse
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import com.fcmchat.fcmchat.invite.data.UserEntity
import com.google.gson.Gson
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 09.08.2018.
 */
class NavInteractor : INavInteractor {

    @Inject lateinit var fcmRepo: IFcmRepo

    init {
        App.injector.navComponent.inject(this)
    }

    override fun acceptInvitation(request: InviteRequestEvent): Completable {
        val response = InviteResponse(request, fcmRepo.getFcmKey(), fcmRepo.getUserName(), result = UserEntity.OK_RESULT)

        return fcmRepo.sendTo(request.userKey, InviteResponseEvent().getKey(), Gson().toJson(response))
    }
}