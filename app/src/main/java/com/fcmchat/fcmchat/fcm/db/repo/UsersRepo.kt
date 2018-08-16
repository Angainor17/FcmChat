package com.fcmchat.fcmchat.fcm.db.repo

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.AppDatabase
import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class UsersRepo : IUsersRepo {

    @Inject lateinit var db: AppDatabase

    init {
        App.injector.navComponent.inject(this)
    }

    override fun addUsers(userEntity: UserEntity, chain: ChainEntity): Completable =
            Completable.fromCallable {
                userEntity.chainKey = chain.key
                db.userDao().insert(userEntity)
            }

    override fun getUsers(chain: ChainEntity): Flowable<ArrayList<UserEntity>> =
            db.userDao().getAllUser().map { ArrayList(it) }
}