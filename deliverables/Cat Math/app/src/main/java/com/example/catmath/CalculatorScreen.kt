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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.catmath.ui.theme.CatMathTheme

@ExperimentalMaterial3Api
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