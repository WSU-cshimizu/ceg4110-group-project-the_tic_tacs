// UserPreferences.kt
package com.example.catmath

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("CatMathPrefs", Context.MODE_PRIVATE)

    fun getUsername(): String? {
        return prefs.getString("username", null)
    }

    fun setUsername(username: String) {
        prefs.edit().putString("username", username).apply()
    }

    fun getXP(): Int {
        return prefs.getInt("currentXP", 0)
    }

    fun setXP(xp: Int) {
        prefs.edit().putInt("currentXP", xp).apply()
    }

    fun addXP(xp: Int) {
        val currentXP = getXP()
        setXP(currentXP + xp)
    }
}
