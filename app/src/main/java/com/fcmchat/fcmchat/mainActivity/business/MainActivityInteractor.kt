package com.fcmchat.fcmchat.mainActivity.business

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.mainActivity.data.IMainActivityRepo
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 31.07.2018.
 */
class MainActivityInteractor : IMainActivityInteractor {

    @Inject lateinit var repo: IMainActivityRepo

    init {
        App.injector.mainActivityComponent.inject(this)
    }

    override fun sendMessageAll(message: String): Completable = repo.sendMessageAll(message)


    override fun sendMessageTo(key: String, message: String): Completable = repo.sendMessageTo(key, message)

    override fun saveFcmKey(key: String) {
        repo.saveFcmKey(key)
    }
}