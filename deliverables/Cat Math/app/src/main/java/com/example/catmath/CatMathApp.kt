package com.example.catmath

// CatMathApp.kt

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catmath.ui.theme.CatMathTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatMathApp() {
    val navController = rememberNavController()
    val xpManager = remember { XPManager() }

    CatMathTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(currentXP = xpManager.getCurrentXP(), onNavigateToCalculator = { navController.navigate("calculator") })
            }
            composable("calculator") {
                CalculatorScreen(
                    onNavigateBack = {
                        xpManager.addXP(5)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
