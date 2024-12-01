// CatMathApp.kt
package com.example.catmath

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catmath.ui.theme.CatMathTheme

@ExperimentalMaterial3Api
@Composable
fun CatMathApp(userPreferences: UserPreferences) {
    val navController = rememberNavController()

    CatMathTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(
                    username = userPreferences.getUsername() ?: "User",
                    currentXP = userPreferences.getXP(),
                    onNavigateToCalculator = { navController.navigate("calculator") }
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
        }
    }
}
