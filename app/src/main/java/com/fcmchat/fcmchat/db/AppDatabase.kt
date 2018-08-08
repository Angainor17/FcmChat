package com.fcmchat.fcmchat.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fcmchat.fcmchat.chains.data.ChainDao
import com.fcmchat.fcmchat.chains.data.ChainEntity
import com.fcmchat.fcmchat.invite.data.UserDao
import com.fcmchat.fcmchat.invite.data.UserEntity
import com.fcmchat.fcmchat.transactions.data.TransactionEntity
import com.fcmchat.fcmchat.transactions.data.TransactionsDao

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