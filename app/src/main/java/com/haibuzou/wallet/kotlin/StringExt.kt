@file:JvmName("StringExt")
package com.haibuzou.wallet.kotlin

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5() : String{
    var secretBytes: ByteArray? = null
    secretBytes = try {
        MessageDigest.getInstance("md5").digest(
            this.toByteArray()
        )
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException("没有md5这个算法！")
    }
    var md5code = BigInteger(1, secretBytes).toString(16)
    for (i in 0 until 32 - md5code.length) {
        md5code = "0$md5code"
    }
    return md5code
}