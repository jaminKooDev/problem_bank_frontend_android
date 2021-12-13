package kr.co.metasoft.android.metaojt.global

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object Preferences {

    private const val TOKEN: String = "token"
    private const val USERNAME: String = "username"
    private const val PASSWORD: String = "password"
    private const val NAME: String = "name"
    private const val PHONE_NUM: String = "phoneNum"
    private const val GENDER: String = "gender"
    private const val USER: String = "user"

    private lateinit var preferences: SharedPreferences

    fun setToken(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(TOKEN, value)
        editor.apply()
    }

    fun getUser(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(USER, "")
    }

    fun setUser(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(USER, value)
        editor.apply()
    }

    fun getToken(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(TOKEN, "")
    }

    fun setUsername(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(USERNAME, value)
        editor.apply()
    }

    fun getUsername(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(USERNAME, "")
    }

    fun setPassword(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(PASSWORD, value)
        editor.apply()
    }

    fun getPassword(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(PASSWORD, "")
    }

    fun setPhoneNum(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(PHONE_NUM, value)
        editor.apply()
    }

    fun getPhoneNum(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(PHONE_NUM, "")
    }

    fun setName(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(NAME, value)
        editor.apply()
    }

    fun getName(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(NAME, "")
    }

    fun setGender(context: Context, value: String) {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(GENDER, value)
        editor.apply()
    }

    fun getGender(context: Context) : String? {
        preferences = context.getSharedPreferences("API", Activity.MODE_PRIVATE)
        return preferences.getString(GENDER, "")
    }

}