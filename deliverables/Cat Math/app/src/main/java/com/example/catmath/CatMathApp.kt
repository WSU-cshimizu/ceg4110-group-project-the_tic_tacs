// CatMathApp.kt
package com.example.catmath

import MathProblemsScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catmath.ui.theme.CatMathTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatMathApp(userPreferences: UserPreferences) {
    val navController = rememberNavController()

    CatMathTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(
                    username = userPreferences.getUsername() ?: "User",
                    currentXP = userPreferences.getXP(),
                    onNavigateToCalculator = { navController.navigate("calculator") },
                    onNavigateToMathProblems = { navController.navigate("math_problems") },
                    onNavigateToMathDrill = { navController.navigate("math_drill") }
                )
            }
            composable("calculator") {
                CalculatorScreen(
                    onNavigateBack = {
                        userPreferences.addXP(5)
                        navController.popBackStack()
                    }
                )
            }
            composable("math_problems") {
                MathProblemsScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onXPAdded = { xp ->
                        userPreferences.addXP(xp)
                    }
                )
            }
            composable("math_drill") {
                MathDrillScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onXPAdded = { xp ->
                        userPreferences.addXP(xp)
                    }
                )
            }
        }
    }
}