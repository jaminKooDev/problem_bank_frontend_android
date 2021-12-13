package kr.co.metasoft.android.metaojt.model

import com.google.gson.annotations.SerializedName

data class UserRequestModel(

    @SerializedName("username")
    val id: String,

    @SerializedName("password")
    val pw: String? = null,

    @SerializedName("frontend")
    val frontend: String = "frontend_user"
)