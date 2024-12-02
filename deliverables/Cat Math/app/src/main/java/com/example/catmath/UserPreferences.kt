// UserPreferences.kt
package com.example.catmath

import android.content.Context
import android.content.SharedPreferences

data class UserPreferences(val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("CatMathPrefs", Context.MODE_PRIVATE)

    fun getUsername(): String? {
        return prefs.getString("username", null)
    }

    fun setUsername(username: String) {
        prefs.edit().putString("username", username).apply()
    }

    fun getXP(): Int {
        return prefs.getInt("xp", 0)
    }

    fun addXP(xp: Int) {
        val currentXP = getXP()
        prefs.edit().putInt("xp", currentXP + xp).apply()
    }

    fun deductXP(xp: Int) {
        val currentXP = getXP()
        prefs.edit().putInt("xp", currentXP - xp).apply()
    }

    fun setUserAvatar(avatarResId: Int) {
        prefs.edit().putInt("avatar", avatarResId).apply()
    }

    fun getUserAvatar(): Int {
        return prefs.getInt("avatar", R.drawable.cute_boy)
    }
}
