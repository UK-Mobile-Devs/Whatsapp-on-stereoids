package com.example.whatsapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.whatsapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Boolean.getBoolean
import javax.inject.Inject

class SPManager @Inject constructor(@ApplicationContext private val context: Context, private val preferences: SharedPreferences) {

    companion object {
        const val DARK_MODE = "android.content.SharedPreferences.darkMode"
        const val LIGHT_MODE = "android.content.SharedPreferences.lightMode"
        const val CHECKED_INT = "android.content.SharedPreferences.checkedInt"


    }

    fun isDarkModeEnabled() = getBoolean(DARK_MODE)
    fun enableDarkMode() = saveBoolean(DARK_MODE, true)
    fun isLightModeEnabled() = getBoolean(LIGHT_MODE)
    fun enableLightMode() = saveBoolean(LIGHT_MODE, true)

    fun getCheckedInteger() : Int {
        return getInt(CHECKED_INT)
    }
    fun saveCheckedInteger(theme : Int) {
        saveInt(CHECKED_INT, theme)
    }


    private fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    private fun saveBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }



    private fun saveInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    private fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }
}