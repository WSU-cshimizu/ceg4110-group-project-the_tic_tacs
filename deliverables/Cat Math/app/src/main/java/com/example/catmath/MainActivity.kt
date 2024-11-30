package com.example.catmath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catmath.ui.theme.CatMathTheme
import com.example.catmath.R

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatMathApp()
        }
    }

    @Composable
    fun CatMathApp() {
        val navController = rememberNavController()
        val xpManager = remember { XPManager() }

        CatMathTheme {
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(currentXP = xpManager.getCurrentXP(), onNavigateToCalculator = { navController.navigate("calculator") })
                }
                composable("calculator") {
                    CalculatorScreen(
                        onNavigateBack = {
                            xpManager.addXP(5)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreen(currentXP: Int, onNavigateToCalculator: () -> Unit) {
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
                                currentXP = currentXP,
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
                    GridButtons(onNavigateToCalculator)
                }
            }
        }
    }

    @Composable
    fun GridButtons(onNavigateToCalculator: () -> Unit) {
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
                Button(onClick = onNavigateToCalculator, shape = RoundedCornerShape(8.dp)) {
                    Text(text = "Calculator")
                }
                Button(onClick = { /* TODO: Launch Math Drill */ }, shape = RoundedCornerShape(8.dp)) {
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
                Button(onClick = { /* TODO: Launch Math Problems */ }, shape = RoundedCornerShape(8.dp)) {
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

    @Composable
    fun CalculatorScreen(onNavigateBack: () -> Unit) {
        var input by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        val calculator = Calculator()
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        onNavigateBack()
                    }) {
                        Text("OK")
                    }
                },
                title = { Text("Great Job!") },
                text = { Text("5 XP earned!") }
            )
        }

        CatMathTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                // Screen Title
                                Text(
                                    text = "Calculator",
                                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                // Back Button
                                IconButton(onClick = { showDialog = true }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_back_arrow),
                                        contentDescription = "Back Button"
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
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
                            .padding(16.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .zIndex(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        // Display input and result
                        Text(
                            text = "Input: $input",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "Result: $result",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CalculatorButtons(onButtonClick = { label ->
                            when (label) {
                                "=" -> {
                                    try {
                                        val parts = input.split(" ")
                                        if (parts.size == 3) {
                                            val a = parts[0].toDouble()
                                            val operator = parts[1]
                                            val b = parts[2].toDouble()
                                            result = when (operator) {
                                                "+" -> calculator.add(a, b).toString()
                                                "-" -> calculator.subtract(a, b).toString()
                                                "×" -> calculator.multiply(a, b).toString()
                                                "÷" -> calculator.divide(a, b).toString()
                                                else -> "Error"
                                            }
                                        }
                                    } catch (e: Exception) {
                                        result = "Error"
                                    }
                                }
                                "C" -> {
                                    input = ""
                                    result = ""
                                }
                                "⌫" -> {
                                    if (input.isNotEmpty()) {
                                        input = input.dropLast(1)
                                    }
                                }
                                else -> {
                                    input += if (label in listOf("+", "-", "×", "÷")) " $label " else label
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun CalculatorButtons(onButtonClick: (String) -> Unit) {
        val buttonLabels = listOf(
            listOf("7", "8", "9", "÷"),
            listOf("4", "5", "6", "×"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "=", "+"),
            listOf("C", "⌫")
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (row in buttonLabels) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (label in row) {
                        Button(
                            onClick = { onButtonClick(label) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f).padding(4.dp)
                        ) {
                            Text(text = label)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

class Calculator {
    fun add(a: Double, b: Double): Double {
        return a + b
    }

    fun subtract(a: Double, b: Double): Double {
        return a - b
    }

    fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    fun divide(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        return a / b
    }
}

class XPManager {
    private var currentXP: Int = 0

    fun getCurrentXP(): Int {
        return currentXP
    }

    fun addXP(xp: Int) {
        currentXP += xp
    }
}
