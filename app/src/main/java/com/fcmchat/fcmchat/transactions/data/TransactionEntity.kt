package com.fcmchat.fcmchat.transactions.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fcmchat.fcmchat.chains.data.ChainEntity
import com.fcmchat.fcmchat.fcm.transactions.NewUserTransaction
import com.fcmchat.fcmchat.invite.data.UserEntity

/**
 * Created by Voronin Igor on 08.08.2018.
 */
@Entity(tableName = "transactions")
class TransactionEntity() {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0

    @ColumnInfo(name = "userName") var userName = ""
    @ColumnInfo(name = "userKey") var userKey = ""

    @ColumnInfo(name = "data") var data = ""
    @ColumnInfo(name = "type") var type = ""

    @ColumnInfo(name = "hashCode") var hash = ""

    @ColumnInfo(name = "chain_id") var chainId: Long = 0
    @ColumnInfo(name = "chain_name") var chainName = ""


    constructor(trans: NewUserTransaction, hash: String) : this() {
        val parentUser = trans.parentUserEntity
        val newUser = trans.newUserEntity

        userName = parentUser!!.name
        userKey = parentUser.key
        data = "${parentUser.name} add  ${newUser!!.name} to ${newUser.chainName}"
        chainId = parentUser.chainId
        chainName = parentUser.chainName
        type = "Add User"

        this.hash = hash
    }

    constructor(userEntity: UserEntity, chain: ChainEntity) : this() {
        userName = userEntity.name
        userKey = userEntity.key
        data = "${userEntity.name} add  ${userEntity.name} to ${userEntity.chainName}"
        chainId = chain.id
        chainName = chain.name
        type = "Add User"

        this.hash = ""
    }
}