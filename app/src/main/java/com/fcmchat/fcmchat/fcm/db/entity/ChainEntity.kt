package com.fcmchat.fcmchat.fcm.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Voronin Igor on 06.08.2018.
 * DM means Data Model
 */
@Entity(tableName = "microchain")
class ChainEntity() {

    @PrimaryKey(autoGenerate = true) var id: Long = 0
    @ColumnInfo(name = "name") var name: String = ""
    @ColumnInfo(name = "key") var key: String = ""
    @ColumnInfo(name = "timestamp") var timestamp: Long = Calendar.getInstance().timeInMillis

    fun init() {
        key = applySha256(name + timestamp)
    }

    init {
        init()
    }

    constructor(name: String, key: String) : this() {
        this.name = name
        this.key = key
        this.timestamp = Calendar.getInstance().timeInMillis
    }
}