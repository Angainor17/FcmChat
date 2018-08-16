package com.fcmchat.fcmchat.fcm.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Entity(tableName = "transactions")
class TransactionEntity {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0

    @ColumnInfo(name = "data") var data = ""
    @ColumnInfo(name = "type") var type = ""

    @ColumnInfo(name = "hashCode") var hash = ""
    @ColumnInfo(name = "prevHash") var prevHash = ""

    @ColumnInfo(name = "chain_key") var chainKey: String = ""

    fun initHash() {
        hash = applySha256(data + type + prevHash + chainKey)
    }

    companion object {
        const val NEW_USER_TYPE = "New User"
        const val MESSAGE_TYPE = "Message"
    }
}