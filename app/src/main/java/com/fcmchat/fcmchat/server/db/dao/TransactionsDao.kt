package com.fcmchat.server.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.fcmchat.server.db.entity.TransactionEntity
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Dao()
interface TransactionsDao {

    @Query("SELECT * FROM transactions")
    fun getAll(): Flowable<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getById(id: Long): Flowable<TransactionEntity?>?

    @Insert fun insert(transaction: TransactionEntity)
    @Update fun update(transaction: TransactionEntity)
    @Delete fun delete(transaction: TransactionEntity)

}