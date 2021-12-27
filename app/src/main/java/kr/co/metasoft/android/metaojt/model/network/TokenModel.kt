package kr.co.metasoft.android.metaojt.model.network

data class TokenModel(
    val exp: Int,
    val iat: Int,
    val menuList: List<Menu>,
    val nbf: Int,
    val person: Person,
    val roleList: List<Role>,
    val sub: String,
    val treeMenuList: List<TreeMenu>,
    val user: User,
    val userAgentSummaryInfo: UserAgentSummaryInfo
)