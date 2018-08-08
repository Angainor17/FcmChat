package com.fcmchat.fcmchat.invite.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUser(): Flowable<UserEntity>

    @Insert fun insert(user: UserEntity)
    @Delete fun delete(user: UserEntity)
    @Update fun update(user: UserEntity)

}