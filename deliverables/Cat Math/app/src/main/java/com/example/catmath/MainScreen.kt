package com.example.catmath

// MainScreen.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(username: String, currentXP: Int, onNavigateToCalculator: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    // User Avatar and Username at the top bar
                    UserProfile(username = username, currentXP = currentXP)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        MainContent(innerPadding = innerPadding, onNavigateToCalculator = onNavigateToCalculator, username = username)
    }
}

@Composable
fun MainContent(innerPadding: PaddingValues, onNavigateToCalculator: () -> Unit, username: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.cute_cat),
            contentDescription = "Cat Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(-1f)
        )

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .zIndex(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Greeting(name = username, modifier = Modifier.padding(top = 16.dp))
            Spacer(modifier = Modifier.height(24.dp))
            GridButtons(onNavigateToCalculator)
        }
    }
}
