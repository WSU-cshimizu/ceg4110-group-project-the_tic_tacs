// MainActivity.kt
package com.example.catmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)

        setContent {
            CatMathEntryPoint(userPreferences)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CatMathEntryPoint(userPreferences: UserPreferences) {
        // Using rememberSaveable ensures the username persists across configuration changes like screen rotation.
        var username by rememberSaveable { mutableStateOf(userPreferences.getUsername()) }

        if (username == null) {
            // Show the UsernameInputScreen if username is not set
            UsernameInputScreen(onUsernameEntered = { name ->
                userPreferences.setUsername(name) // Save the username
                username = name // Update state to trigger navigation to main content
            })
        } else {
            // Username exists, so navigate to the main content
            CatMathApp(userPreferences)
        }
    }
}
