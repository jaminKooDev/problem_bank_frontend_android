package kr.co.metasoft.android.metaojt.model

import com.google.gson.annotations.SerializedName

data class PersonModel(
    @SerializedName("username")
    var name: String
)