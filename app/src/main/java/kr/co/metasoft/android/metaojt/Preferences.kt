package kr.co.metasoft.android.metaojt

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object Preferences {

    val TOKEN: String = "token"

    private lateinit var preferences: SharedPreferences

    fun setToken(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(TOKEN, value)
        editor.apply()
    }

    fun getToken(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(TOKEN, "")
    }

}