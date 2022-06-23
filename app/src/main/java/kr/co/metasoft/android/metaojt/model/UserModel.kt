package kr.co.metasoft.android.metaojt.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password : String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("phoneNum")
    var phoneNum: String? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("userTypeCode")
    var userTypeCode: Long = 4L,

    var authId: Long? = null
)