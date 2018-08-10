package com.fcmchat.fcmchat.invite.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Entity(tableName = "users")
class UserEntity {

    @PrimaryKey(autoGenerate = true) var id: Long = 0
    @ColumnInfo(name = "name") var name: String = ""
    @ColumnInfo(name = "key") var key: String = ""
    @ColumnInfo(name = "policy") var policy: Int = SEND_AND_INVITE_POLICY

    companion object {
        @Ignore const val READ_ONLY_POLICY = 1
        @Ignore const val INVITE_POLICY = 2
        @Ignore const val SEND_POLICY = 4
        @Ignore const val SEND_AND_INVITE_POLICY = 5

        fun getPolicy(sendFlag: Boolean, inviteFlag: Boolean) =
                if (sendFlag) if (inviteFlag) SEND_AND_INVITE_POLICY else SEND_POLICY
                else if (inviteFlag) INVITE_POLICY else READ_ONLY_POLICY

        @Ignore const val OK_RESULT = 0
        @Ignore const val DECLINED_RESULT = 1
    }
}