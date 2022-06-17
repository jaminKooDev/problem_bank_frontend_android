package kr.co.metasoft.android.metaojt.global

object ValidationPattern {
    const val ID_LENGTH_PATTERN = "^(?=.{8,20}$)$"
    const val ID_ONLY_CHARACTER_AND_NUMBER_PATTERN = "[^a-zA-Z0-9._]"
    const val PASSWORD_PATTERN = "[^a-zA-Z0-9~`!@#$%^&*()-+=_]"
    const val PHONE_NUM_PATTERN = "^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})\$"
    const val NAME_PATTERN = "[^a-zA-Z가-힣]"
    const val ONLY_NUMBER_PATTERN = "/^[0-9]/g"
}