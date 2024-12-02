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
import com.example.catmath.MathProblemButtons
import com.example.catmath.R
import com.example.catmath.ui.theme.CatMathTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathProblemsScreen(onNavigateBack: () -> Unit, onXPAdded: (Int) -> Unit) {
    val context = LocalContext.current
    var question by remember { mutableStateOf(generateQuestion()) }
    var userAnswer by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var earnedXP by remember { mutableStateOf(0) }
    var correctAnswer by remember { mutableStateOf(0) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var randomFact by remember { mutableStateOf(getRandomCatFact(context)) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                if (!isAnswerCorrect) {
                    question = generateQuestion() // Generate a new question after an incorrect answer
                    userAnswer = ""
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    if (isAnswerCorrect) {
                        question = generateQuestion() // Generate a new question after a correct answer
                        userAnswer = ""
                        earnedXP += 5 // Add XP for each correct answer
                    } else {
                        earnedXP = 0
                    }
                    if (isAnswerCorrect) {
                        onXPAdded(earnedXP)
                    }
                    earnedXP = 0
                    randomFact = getRandomCatFact(context)
                }) {
                    Text("OK")
                }
            },
            title = { Text(if (isAnswerCorrect) "Great Job!" else "Incorrect Answer") },
            text = {
                Column {
                    Text(if (isAnswerCorrect) "5 XP earned!" else "That answer was incorrect. The correct answer is: $correctAnswer")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Fun Fact: $randomFact")
                }
            }
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
                                text = "Math Problems",
                                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            // Back Button
                            IconButton(onClick = { onNavigateBack() }) {
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
                                    isAnswerCorrect = true
                                    showDialog = true
                                } else {
                                    isAnswerCorrect = false
                                    showDialog = true
                                }
                            } catch (e: Exception) {
                                isAnswerCorrect = false
                                showDialog = true
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



fun generateQuestion(): String {
    val a = (1..99).random()
    val b = (1..99).random()
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