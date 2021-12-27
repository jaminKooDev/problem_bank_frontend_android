package kr.co.metasoft.android.metaojt.global

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class Preferences(context: Context) {

    private val prefName = "API"
    private val prefs = context.getSharedPreferences(prefName, MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token", null)
        set(value) {
            prefs.edit().putString("token", value).apply()
        }

    var username: String?
        get() = prefs.getString("username", null)
        set(value) {
            prefs.edit().putString("username", value).apply()
        }
}