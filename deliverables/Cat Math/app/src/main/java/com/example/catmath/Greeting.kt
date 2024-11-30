package com.example.catmath

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment // Make sure to import this
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! \n Please press a button to play:",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter), // Centers the text
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // Sets font to bold
        textAlign = TextAlign.Center // Centers the text alignment
    )
}
