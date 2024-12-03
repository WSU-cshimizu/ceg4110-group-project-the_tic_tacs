//THIS ENTIRE PROJECT WAS WRITTEN WITH THE HELP OF CHATGPT. I JUST WANT TO GET THAT OUT THERE
//RIGHT OFF THE BAT.
// Authors: Jessica, Ethan, Ben, Abit, and Lilliane from The_Tic_Tacs
// This is an Android app for a kids learning game.
//
// MainActivity.kt
package com.example.catmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext

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
        val context = LocalContext.current
        var username by rememberSaveable { mutableStateOf(userPreferences.getUsername()) }

        // Instead of conditionally displaying composables, use a state to handle the screen logic.
        if (username.isNullOrEmpty()) {
            UsernameInputScreen(onUsernameEntered = { name ->
                userPreferences.setUsername(name) // Save the username
                username = name // Update state to trigger navigation to main content
            })
        } else {
            // Username exists, navigate to the main content
            CatMathApp(userPreferences)
        }
    }
}
