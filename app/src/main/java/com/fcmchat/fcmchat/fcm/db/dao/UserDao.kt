package com.fcmchat.fcmchat.fcm.db.dao

import android.arch.persistence.room.*
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUser(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE chain_key=:chainId")
    fun getUsersByChainId(chainId: String): Flowable<List<UserEntity>>

    @Insert fun insert(user: UserEntity)
    @Delete fun delete(user: UserEntity)
    @Update fun update(user: UserEntity)

}