package com.fcmchat.fcmchat.fcm.db.repo

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface IUsersRepo {
    fun addUsers(userEntity: UserEntity, chain: ChainEntity): Completable
    fun getUsers(chain: ChainEntity): Flowable<ArrayList<UserEntity>>
}