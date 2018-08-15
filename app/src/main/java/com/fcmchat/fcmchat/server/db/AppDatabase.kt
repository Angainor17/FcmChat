package com.fcmchat.server.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fcmchat.server.db.dao.TransactionsDao
import com.fcmchat.server.db.entity.TransactionEntity

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionsDao
}