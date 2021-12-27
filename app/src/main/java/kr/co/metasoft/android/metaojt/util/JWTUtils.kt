package kr.co.metasoft.android.metaojt.util

import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.nio.charset.Charset

object JWTUtils {
    @Throws(Exception::class)
    fun decoded(JWTEncoded: String) {
        try {
            val split = JWTEncoded.split(".")
            Log.d("JWT_DECODED", "Header: ${getJson(split[0])}")
            Log.d("JWT_DECODED", "Payload: ${getJson(split[1])}")
            Log.d("JWT_DECODED", "Signature: ${getJson(split[2])}")

        } catch (e: UnsupportedEncodingException) {
            //Error
        }
    }

    fun getPayload(JWTEncoded: String): String? {
        try {
            val split = JWTEncoded.split(".")
            return getJson(split[1])

        } catch (e: UnsupportedEncodingException) {
            //Error
        }
        return null
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, Charset.forName("UTF-8"))
    }
}