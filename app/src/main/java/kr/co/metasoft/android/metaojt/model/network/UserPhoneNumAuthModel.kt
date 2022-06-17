package kr.co.metasoft.android.metaojt.model.network

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class UserPhoneNumAuthModel(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("phoneNum")
    val phoneNum: String,

    @SerializedName("expired")
    val expired: String? = null,

    @SerializedName("authToken")
    val authToken: String? = null,

    @SerializedName("statusCode")
    val statusCode: Long? = null,

    @SerializedName("responseMessage")
    val responseMessage: String? = null
)