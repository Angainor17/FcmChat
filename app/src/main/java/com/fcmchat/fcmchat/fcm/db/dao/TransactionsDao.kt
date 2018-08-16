package com.fcmchat.fcmchat.fcm.db.dao

import android.arch.persistence.room.*
import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Dao
interface TransactionsDao {

    @Query("SELECT * FROM transactions")
    fun getAll(): Flowable<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE chain_key=:chainKey")
    fun getAllBuChainKey(chainKey: String): Flowable<List<TransactionEntity>>

    @Insert fun insert(transaction: TransactionEntity)
    @Update fun update(transaction: TransactionEntity)
    @Delete fun delete(transaction: TransactionEntity)

}