package com.fcmchat.fcmchat.chains.data

import android.content.Context
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.db.AppDatabase
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsRepo : IChainsRepo {

    @Inject lateinit var context: Context
    @Inject lateinit var db: AppDatabase

    init {
        App.injector.chainsComponent.inject(this)
    }

    override fun addChain(newChain: ChainEntity) = db.chainDao().insert(newChain)

    override fun getAllChains(): Single<ArrayList<ChainEntity>> =
            db.chainDao().getAll().firstOrError().map { ArrayList(it) }

}