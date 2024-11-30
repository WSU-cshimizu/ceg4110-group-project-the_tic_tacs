package com.example.catmath

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun UserAvatar(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.cute_boy),
        contentDescription = "User Avatar",
        modifier = modifier
            .clip(CircleShape), // Avatar size and shape
        contentScale = ContentScale.Crop
    )
}
