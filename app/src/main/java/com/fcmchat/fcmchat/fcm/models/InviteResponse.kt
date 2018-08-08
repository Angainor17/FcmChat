package com.fcmchat.fcmchat.fcm.models

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InviteResponse(
        val topicName: String,
        val userKey: String,
        val result: Int,
        val policy: Int
)