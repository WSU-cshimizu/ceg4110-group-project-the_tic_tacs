package com.example.catmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catmath.ui.theme.CatMathTheme
import com.example.catmath.R

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatMathTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                TopBarContent(
                                    username = "CatMaster",
                                    xp = 70,
                                    xpMax = 100
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Set the background image
                        Image(
                            painter = painterResource(id = R.drawable.cute_cat),
                            contentDescription = "Cat Background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        // Your greeting text on top of the image
                        Greeting(
                            name = "CatMaster",
                            modifier = Modifier.padding(innerPadding)
                                .padding(innerPadding)
                                .align(Alignment.TopCenter)
//                                .padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarContent(username: String, xp: Int, xpMax: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.cute_boy), // Replace with your profile image
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Username and XP Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = username,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            // XP Bar
            LinearProgressIndicator(
                progress = xp.toFloat() / xpMax,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center), // Centers the text
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // Sets font to bold
        textAlign = TextAlign.Center // Centers the text alignment
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CatMathTheme {
        Greeting("Android")
    }
}
