package com.fcmchat.fcmchat.fcm.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fcmchat.fcmchat.fcm.db.dao.ChainDao
import com.fcmchat.fcmchat.fcm.db.dao.TransactionsDao
import com.fcmchat.fcmchat.fcm.db.dao.UserDao
import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Database(
        entities = [ChainEntity::class, TransactionEntity::class, UserEntity::class],
        version = 1,
        exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun chainDao(): ChainDao
    abstract fun transactionDao(): TransactionsDao
    abstract fun userDao(): UserDao
}