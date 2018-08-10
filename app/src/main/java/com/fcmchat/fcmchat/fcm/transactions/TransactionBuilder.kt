package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.invite.data.UserEntity

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class TransactionBuilder {
    companion object {
        fun userConvert(userEntity: UserEntity) = NewUserTransaction(userEntity)

        fun transactions() = arrayListOf(
                NewUserTransaction()
        )
    }
}