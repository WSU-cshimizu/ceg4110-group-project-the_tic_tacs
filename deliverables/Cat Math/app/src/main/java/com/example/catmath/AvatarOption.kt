package com.example.catmath

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AvatarOption(avatarResId: Int, isSelected: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.graphicsLayer(scaleX = if (isSelected) 1.2f else 1.0f, scaleY = if (isSelected) 1.2f else 1.0f)) {
        Image(
            painter = painterResource(id = avatarResId),
            contentDescription = "Avatar Option",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, if (isSelected) Color.Green else Color.Transparent, CircleShape)
        )
    }
}