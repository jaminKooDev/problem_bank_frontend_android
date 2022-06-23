package kr.co.metasoft.android.metaojt.model

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("userDto")
    var userDto: UserModel,

    @SerializedName("personDto")
    var personDto: PersonModel,
)