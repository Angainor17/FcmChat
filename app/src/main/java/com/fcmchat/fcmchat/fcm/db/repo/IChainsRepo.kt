package com.fcmchat.fcmchat.fcm.db.repo

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Voronin Igor on 06.08.2018.
 */
interface IChainsRepo {
    fun addChain(newChain: ChainEntity): Completable
    fun getAllChains(): Flowable<ArrayList<ChainEntity>>
    fun getChainByKey(chainKey: String): Single<ChainEntity>
}