package com.example.catmath

class XPManager {
    private var currentXP: Int = 0

    fun getCurrentXP(): Int {
        return currentXP
    }

    fun addXP(xp: Int) {
        currentXP += xp
    }
}