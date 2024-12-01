// UserProfile.kt
package com.example.catmath

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserProfile(username: String, currentXP: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        UserAvatar(modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        UserNameText(
            username = username,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ProgressBarWithText(
            currentXP = currentXP,
            xpMax = 100
        )
    }
}
