package kr.co.metasoft.android.metaojt.model.network

data class TreeMenu(
    val childrenCount: Int,
    val createdDate: String,
    val depth: Int,
    val description: String,
    val icon: Any,
    val id: Int,
    val lastModifiedDate: String,
    val name: String,
    val namePath: String,
    val parentId: Any,
    val path: String,
    val ranking: Int,
    val rankingPath: String,
    val show: String
)