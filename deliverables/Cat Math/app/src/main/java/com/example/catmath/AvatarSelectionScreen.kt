package com.example.catmath

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun AvatarSelectionScreen(currentXP: Int, currentAvatar: Int, onAvatarSelected: (Int) -> Unit, onNavigateBack: () -> Unit) {
    var selectedAvatar by remember { mutableStateOf(currentAvatar) }
    var saveClicked by remember { mutableStateOf(false) }

    if (currentXP < 50) {
        AlertDialog(
            onDismissRequest = onNavigateBack,
            confirmButton = {
                TextButton(onClick = onNavigateBack) {
                    Text("OK")
                }
            },
            title = { Text("Not Enough XP") },
            text = { Text("You need at least 50 XP to change your avatar.") }
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Select Your Avatar") },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back_arrow),
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Display avatar options with visual cue for selected avatar
                AvatarOption(R.drawable.cat_pumpkin_mummy, selectedAvatar == R.drawable.cat_pumpkin_mummy) {
                    selectedAvatar = R.drawable.cat_pumpkin_mummy
                }
                AvatarOption(R.drawable.cute_girl, selectedAvatar == R.drawable.cute_girl) {
                    selectedAvatar = R.drawable.cute_girl
                }
                AvatarOption(R.drawable.cute_boy, selectedAvatar == R.drawable.cute_boy) {
                    selectedAvatar = R.drawable.cute_boy
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    if (selectedAvatar != currentAvatar && currentXP >= 50) {
                        onAvatarSelected(selectedAvatar)
                        saveClicked = true
                    }
                }) {
                    Text("Save and Continue")
                }

                // Trigger navigation back only if the save was clicked and avatar update is completed
                if (saveClicked) {
                    // Wait for composable recomposition
                    LaunchedEffect(Unit) {
                        onNavigateBack()
                    }
                }
            }
        }
    }
}
