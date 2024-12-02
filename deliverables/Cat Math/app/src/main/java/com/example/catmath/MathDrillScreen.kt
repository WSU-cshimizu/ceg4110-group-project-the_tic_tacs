package com.example.catmath

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.catmath.ui.theme.CatMathTheme
import getRandomCatFact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathDrillScreen(onNavigateBack: () -> Unit, onXPAdded: (Int) -> Unit) {
    val context = LocalContext.current
    var showInitialDialog by remember { mutableStateOf(true) }
    var countdownStarted by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableStateOf(60) }
    var timerRunning by remember { mutableStateOf(false) }
    var question by remember { mutableStateOf(generateDrillQuestion(11)) }
    var userAnswer by remember { mutableStateOf("") }
    var earnedXP by remember { mutableStateOf(0) }
    var correctAnswer by remember { mutableStateOf(0) }
    var correctCount by remember { mutableStateOf(0) }
    var randomFact by remember { mutableStateOf(getRandomCatFact(context)) }

    if (showInitialDialog) {
        AlertDialog(
            onDismissRequest = { showInitialDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showInitialDialog = false
                    countdownStarted = true
                    // Start a 5-second countdown
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000L)
                        countdownStarted = false
                        timerRunning = true
                    }
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = onNavigateBack) {
                    Text("No")
                }
            },
            title = { Text("Would you like to play?") },
            text = { Text("This is a timed math drill. Are you ready?") }
        )
    }

    if (countdownStarted) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    countdownStarted = false
                }) {
                    Text("OK")
                }
            },
            title = { Text("Get Ready!") },
            text = { Text("The game will start in 5 seconds!") }
        )
    }

    if (!showInitialDialog && !countdownStarted) {
        if (timerRunning) {
            LaunchedEffect(timeRemaining) {
                if (timeRemaining > 0) {
                    delay(1000L)
                    timeRemaining--
                } else {
                    timerRunning = false
                    earnedXP = correctCount * 5
                    onXPAdded(earnedXP)
                    showInitialDialog = true
                    randomFact = getRandomCatFact(context)
                }
            }
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
                                    text = "Math Drill",
                                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                // Back Button
                                IconButton(onClick = {
                                    timerRunning = false
                                    onNavigateBack()
                                }) {
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

                        // Display Timer
                        Text(
                            text = "Time Remaining: $timeRemaining seconds",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )

                        // Display the question
                        Text(
                            text = "Question: $question",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp)
                        )

                        // Input field for user answer
                        OutlinedTextField(
                            value = userAnswer,
                            onValueChange = { userAnswer = it },
                            label = { Text("Your Answer") },
                            modifier = Modifier.padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = {
                            if (userAnswer.isNotEmpty()) {
                                try {
                                    correctAnswer = evaluateQuestion(question)
                                    if (userAnswer.toInt() == correctAnswer) {
                                        correctCount++
                                        question = generateDrillQuestion(correctAnswer)
                                        userAnswer = ""
                                    } else {
                                        timerRunning = false
                                        earnedXP = correctCount * 5
                                        onXPAdded(earnedXP)
                                        showInitialDialog = true
                                        randomFact = getRandomCatFact(context)
                                    }
                                } catch (e: Exception) {
                                    timerRunning = false
                                    earnedXP = correctCount * 5
                                    onXPAdded(earnedXP)
                                    showInitialDialog = true
                                    randomFact = getRandomCatFact(context)
                                }
                            }
                        }) {
                            Text("Submit Answer")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Numerical Buttons for user input
                        MathProblemButtons(onButtonClick = { label ->
                            when (label) {
                                "C" -> userAnswer = ""
                                "⌫" -> if (userAnswer.isNotEmpty()) userAnswer = userAnswer.dropLast(1)
                                else -> userAnswer += label
                            }
                        })
                    }
                }
            }
        }
    }
}

fun generateDrillQuestion(previousAnswer: Int = 1): String {
    val a = previousAnswer
    val b = (0..10).random()
    val operator = if ((0..1).random() == 0) "+" else "-"
    return "$a $operator $b"
}

fun evaluateQuestion(question: String): Int {
    val parts = question.split(" ")
    val a = parts[0].toInt()
    val operator = parts[1]
    val b = parts[2].toInt()
    return when (operator) {
        "+" -> a + b
        "-" -> a - b
        else -> 0
    }
}
