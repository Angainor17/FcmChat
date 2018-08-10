package com.fcmchat.fcmchat.chains.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Dao
interface ChainDao {

    @Query("SELECT * FROM microchain")
    fun getAll(): Flowable<List<ChainEntity>>

    @Query("SELECT * FROM microchain WHERE id = :id")
    fun getById(id: Long): Flowable<ChainEntity?>?

    @Query("SELECT * FROM microchain WHERE name = :name")
    fun getByName(name: String): Single<ChainEntity>

    @Insert fun insert(chain: ChainEntity)
    @Update fun update(chain: ChainEntity)
    @Delete fun delete(chain: ChainEntity)
}