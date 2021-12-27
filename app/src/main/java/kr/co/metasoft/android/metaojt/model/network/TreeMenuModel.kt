package kr.co.metasoft.android.metaojt.model.network

import com.google.gson.annotations.SerializedName

data class TreeMenuModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("parentId")
    val parentId: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("path")
    val path: String,

    @SerializedName("ranking")
    val ranking: String,

    @SerializedName("show")
    val show: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("depth")
    val depth: Int,

    @SerializedName("namePath")
    val namePath: String,

    @SerializedName("rankingPath")
    val rankingPath: String,

    @SerializedName("childrenCount")
    val childrenCount: Int
)