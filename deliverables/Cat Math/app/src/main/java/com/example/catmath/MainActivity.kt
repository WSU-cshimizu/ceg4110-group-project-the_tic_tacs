package com.example.catmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.catmath.ui.theme.CatMathTheme
import com.example.catmath.R

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatMathTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                // User Avatar and Username at the top bar
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                                ) {
                                    UserAvatar(modifier = Modifier.size(40.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    UserNameText(
                                        username = "CatMaster",
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    ProgressBarWithText(
                                        currentXP = 70,
                                        xpMax = 100
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Background Image
                        Image(
                            painter = painterResource(id = R.drawable.cute_cat),
                            contentDescription = "Cat Background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .zIndex(-1f)
                        )

                        // Content above the background
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
                            // Greeting text
                            Greeting(
                                name = "CatMaster",
                                modifier = Modifier.padding(top = 16.dp)
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Buttons in a grid layout
                            GridButtons()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun GridButtons() {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Ensure padding to avoid touching the edges
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly
            ) {
                Button(onClick = { /* TODO: Launch Calculator */ },
                    shape = RoundedCornerShape(8.dp)) {
                    Text(text = "Calculator")
                }
                Button(onClick = { /* TODO: Launch Math Drill */ },
                    shape = RoundedCornerShape(8.dp)) {
                    Text(text = "Math Drill")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly
            ) {
                Button(onClick = { /* TODO: Launch Math Problems */ },
                    shape = RoundedCornerShape(8.dp)) {
                    Text(text = "Math Problems")
                }
            }
        }
    }

    @Composable
    fun ProgressBarWithText(currentXP: Int, xpMax: Int) {
        val progress = currentXP.toFloat() / xpMax.toFloat()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // ProgressBar
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = MaterialTheme.colorScheme.primary
            )
            // XP Text
            Text(
                text = "$currentXP / $xpMax XP",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

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

    @Composable
    fun UserNameText(username: String, modifier: Modifier = Modifier) {
        Text(
            text = username,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}
