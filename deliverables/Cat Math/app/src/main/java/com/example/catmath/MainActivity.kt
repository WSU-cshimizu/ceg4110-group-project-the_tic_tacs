package com.example.catmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catmath.ui.theme.CatMathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatMathTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("screen1") { Screen1(navController) }
                        composable("screen2") { Screen2(navController) }
                        composable("screen3") { Screen3(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Welcome to CatMath!")

        // Buttons to navigate to different screens
        Button(onClick = { navController.navigate("screen1") }) {
            Text("Go to Screen 1")
        }

        Button(onClick = { navController.navigate("screen2") }) {
            Text("Go to Screen 2")
        }

        Button(onClick = { navController.navigate("screen3") }) {
            Text("Go to Screen 3")
        }
    }
}

@Composable
fun Screen1(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("This is Screen 1")

        // Back button to go to the previous screen
        Button(onClick = { navController.navigateUp() }) {
            Text("Back")
        }

        // Button to show the dialog (popup screen)
        Button(onClick = { showDialog = true }) {
            Text("Show Popup")
        }

        // Popup dialog
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("This is a Popup Screen!")
                    Button(onClick = { showDialog = false }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Composable
fun Screen2(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("This is Screen 2")

        // Back button to go to the previous screen
        Button(onClick = { navController.navigateUp() }) {
            Text("Back")
        }

        // Button to show the dialog (popup screen)
        Button(onClick = { showDialog = true }) {
            Text("Show Popup")
        }

        // Popup dialog
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("This is a Popup Screen!")
                    Button(onClick = { showDialog = false }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Composable
fun Screen3(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("This is Screen 3")

        // Back button to go to the previous screen
        Button(onClick = { navController.navigateUp() }) {
            Text("Back")
        }

        // Button to show the dialog (popup screen)
        Button(onClick = { showDialog = true }) {
            Text("Show Popup")
        }

        // Popup dialog
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("This is a Popup Screen!")
                    Button(onClick = { showDialog = false }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CatMathTheme {
        HomeScreen(rememberNavController())
    }
}
