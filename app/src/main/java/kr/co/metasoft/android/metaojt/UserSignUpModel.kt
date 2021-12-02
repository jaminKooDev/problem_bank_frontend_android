package kr.co.metasoft.android.metaojt

import com.google.gson.annotations.SerializedName

data class UserSignUpModel (
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password : String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phoneNum")
    val phoneNum: String,

    @SerializedName("gender")
    val gender: String

)