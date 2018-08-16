package com.fcmchat.fcmchat.fcm.db.repo

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.AppDatabase
import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsRepo : IChainsRepo {

    @Inject lateinit var db: AppDatabase

    init {
        App.injector.chainsComponent.inject(this)
    }

    override fun addChain(newChain: ChainEntity) =
            Completable.fromCallable { db.chainDao().insert(newChain) }!!

    override fun getAllChains() = db.chainDao().getAll().map { ArrayList(it) }!!

    override fun getChainByKey(chainKey: String) = db.chainDao().getChainByKey(chainKey)
}