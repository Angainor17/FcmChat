package com.fcmchat.fcmchat.fcm.db.entity

import java.security.MessageDigest

fun applySha256(input: String) = try {
    val digest = MessageDigest.getInstance("SHA-256")
    val hash = digest.digest(input.toByteArray(charset("UTF-8")))
    val hexString = StringBuffer()
    for (i in hash.indices) {
        val hex = Integer.toHexString(0xff and hash[i].toInt())
        if (hex.length == 1) hexString.append('0')
        hexString.append(hex)
    }
    hexString.toString()
} catch (e: Exception) {
    ""
}