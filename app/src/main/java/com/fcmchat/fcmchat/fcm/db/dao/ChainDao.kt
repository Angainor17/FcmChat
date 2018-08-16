package com.fcmchat.fcmchat.fcm.db.dao

import android.arch.persistence.room.*
import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Dao
interface ChainDao {

    @Query("SELECT * FROM microchain")
    fun getAll(): Flowable<List<ChainEntity>>

    @Query("SELECT * FROM microchain WHERE `key`=:chainKey")
    fun getChainByKey(chainKey: String): Single<ChainEntity>

    @Insert fun insert(chain: ChainEntity)
    @Update fun update(chain: ChainEntity)
    @Delete fun delete(chain: ChainEntity)
}