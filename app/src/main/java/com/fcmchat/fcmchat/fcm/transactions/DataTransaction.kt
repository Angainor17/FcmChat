package com.fcmchat.fcmchat.fcm.transactions

import com.google.gson.Gson
import java.security.MessageDigest

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class DataTransaction : FcmTransaction() {

    var data = ""

    override fun initType(): String = "message"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, DataTransaction::class.java)

    }

    fun applySha256(input: String): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            //Applies sha256 to our input,
            val hash = digest.digest(input.toByteArray(charset("UTF-8")))
            val hexString = StringBuffer() // This will contain hash as hexidecimal
            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            return hexString.toString()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}