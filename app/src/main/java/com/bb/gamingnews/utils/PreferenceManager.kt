package com.bb.gamingnews.utils

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager protected constructor(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("Test", Context.MODE_PRIVATE)

    companion object {
        private var preferenceManager: PreferenceManager? = null
        private const val LOGIN = "LOGIN"
        private const val TOKEN = "TOKEN"
        private const val EMAIL = "EMAIL"
        private const val USERNAME = "USERNAME"
        private const val USERID = "USERID"
        private const val PHONE = "PHONE"
        private const val Name = "Name"
        private const val RoleId = "RoleId"
        private const val Image = "Image"

        fun getInstance(context: Context): PreferenceManager {
            if (preferenceManager == null)
                preferenceManager =
                    PreferenceManager(context)
            return preferenceManager as PreferenceManager
        }
    }

    fun setLogin(login: Boolean) {
        preference.edit().putBoolean(LOGIN, login).apply()
    }

    val isLoggedIn: Boolean
        get() = preference.getBoolean(LOGIN, false)

    fun setAuthToken(authToken: String?) {
        preference.edit().putString(TOKEN, authToken).apply()
    }

    val getAuthToken: String
        get() = preference.getString(TOKEN, "").toString()

    fun setEmail(email: String) {
        preference.edit().putString(EMAIL, email).apply()
    }

    val getEmail: String
        get() = preference.getString(EMAIL, "").toString()

    fun setUserName(username: String) {
        preference.edit().putString(USERNAME, username).apply()
    }

    val getUserName: String
        get() = preference.getString(USERNAME, "").toString()


  fun setName(name: String?) {
        preference.edit().putString(Name, name).apply()
    }

    val getName: String
        get() = preference.getString(Name, "").toString()

    fun setPhone(phone: String) {
        preference.edit().putString(PHONE, phone.toString()).apply()
    }

    val getPhone: String
        get() = preference.getString(PHONE, "").toString()

      fun setImage(phone: String?) {
        preference.edit().putString(Image, phone.toString()).apply()
    }

    val getImage: String
        get() = preference.getString(Image, "").toString()




    fun setUserId(userId: Int) {
        preference.edit().putInt(USERID, userId).apply()
    }

    val getUserId: Int
        get() = preference.getInt(USERID, 0)



    fun setRoleId(userId: Int) {
        preference.edit().putInt(RoleId, userId).apply()
    }

    val getRoleId: Int
        get() = preference.getInt(RoleId, 0)



    open fun clear() {
        preference.edit().clear().apply()
    }

}