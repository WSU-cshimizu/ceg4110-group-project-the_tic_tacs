// UserProfile.kt
package com.example.catmath

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserProfile(username: String, currentXP: Int, currentAvatar: Int, onAvatarClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        IconButton(onClick = onAvatarClick) {
            Image(
                painter = painterResource(id = currentAvatar),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = username,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "$currentXP XP",
                style = MaterialTheme.typography.bodySmall
            )
            LinearProgressIndicator(
                progress = currentXP / 100f,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}