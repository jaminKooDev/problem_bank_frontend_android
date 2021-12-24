package kr.co.metasoft.android.metaojt.model.network

import com.google.gson.annotations.SerializedName

data class CodeModel (
    @SerializedName("id")
    val id: Int,

    @SerializedName("parentId")
    val parentId: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("value")
    val value: String?,

    @SerializedName("orderNo")
    val orderNo: Int?,

    @SerializedName("show")
    val show: String?
)