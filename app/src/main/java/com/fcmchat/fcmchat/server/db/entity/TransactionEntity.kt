package com.fcmchat.server.db.entity

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
    @ColumnInfo(name = "hashCode") var hash = ""

}