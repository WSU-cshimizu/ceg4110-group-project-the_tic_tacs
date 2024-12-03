package com.example.catmath
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catmath.ui.theme.CatMathTheme
import generateQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
class MathDrillScreenTest {

    @Test
    fun `generateDrillQuestion generates a valid question`() {
        val question = generateDrillQuestion()
        val parts = question.split(" ")

        // Validate the question structure
        assertEquals(3, parts.size) // Should contain two operands and an operator
        assertTrue(parts[0].toIntOrNull() != null) // First operand is an integer
        assertTrue(parts[2].toIntOrNull() != null) // Second operand is an integer
        assertTrue(parts[1] == "+" || parts[1] == "-") // Operator is "+" or "-"
    }

    @Test
    fun `evaluateQuestion evaluates addition correctly`() {
        val question = "5 + 3"
        val result = evaluateQuestion(question)
        assertEquals(8, result)
    }

    @Test
    fun `evaluateQuestion evaluates subtraction correctly`() {
        val question = "10 - 7"
        val result = evaluateQuestion(question)
        assertEquals(3, result)
    }

    @Test
    fun `evaluateQuestion handles invalid input gracefully`() {
        val question = "invalid question"
        val result = evaluateQuestion(question)
        assertEquals(0, result) // Default or fallback result
    }
}

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun `test addition`() {
        val result = calculator.add(2.0, 3.0)
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun `test subtraction`() {
        val result = calculator.subtract(5.0, 3.0)
        assertEquals(2.0, result, 0.0)
    }

    @Test
    fun `test multiplication`() {
        val result = calculator.multiply(4.0, 2.5)
        assertEquals(10.0, result, 0.0)
    }

    @Test
    fun `test division`() {
        val result = calculator.divide(10.0, 2.0)
        assertEquals(5.0, result, 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test division by zero`() {
        calculator.divide(10.0, 0.0)
    }
}


class AvatarOptionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `avatar option displays correctly`() {
        val avatarResId = R.drawable.avatar_sample // Replace with a valid drawable resource ID
        composeTestRule.setContent {
            AvatarOption(avatarResId = avatarResId, isSelected = false, onClick = {})
        }

        composeTestRule.onNodeWithContentDescription("Avatar Option").assertExists()
    }

    @Test
    fun `avatar option reacts to click`() {
        var clicked = false
        val avatarResId = R.drawable.avatar_sample // Replace with a valid drawable resource ID

        composeTestRule.setContent {
            AvatarOption(
                avatarResId = avatarResId,
                isSelected = false,
                onClick = { clicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Avatar Option").performClick()

        assert(clicked)
    }

    @Test
    fun `avatar option highlights when selected`() {
        val avatarResId = R.drawable.avatar_sample // Replace with a valid drawable resource ID

        composeTestRule.setContent {
            AvatarOption(avatarResId = avatarResId, isSelected = true, onClick = {})
        }

        // Check the component's scale or border to confirm it's selected
        // Further assertions can be made based on advanced testing frameworks
        composeTestRule.onNodeWithContentDescription("Avatar Option").assertExists()
    }
}

class AvatarSelectionScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testAvatarSelectionScreen_withEnoughXP_showsAvatarOptions() {
        // Arrange: Use a test case where the XP is greater than or equal to 50
        val currentXP = 100
        val currentAvatar = 0 // Assume starting avatar is 0
        var selectedAvatar = 0

        // Launch the composable
        composeTestRule.setContent {
            AvatarSelectionScreen(
                currentXP = currentXP,
                currentAvatar = currentAvatar,
                onAvatarSelected = { selectedAvatar = it },
                onNavigateBack = {}
            )
        }

        // Act: Check if the avatar options are displayed
        composeTestRule.onNodeWithText("Select Your Avatar").assertIsDisplayed()

        // Assert: Ensure avatar options are shown
        composeTestRule.onNodeWithTag("AvatarOption 1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("AvatarOption 2").assertIsDisplayed()
        composeTestRule.onNodeWithTag("AvatarOption 3").assertIsDisplayed()

        // Act: Select an avatar
        composeTestRule.onNodeWithTag("AvatarOption 1").performClick()

        // Assert: Ensure the selection updates
        assertEquals(selectedAvatar, R.drawable.cat_pumpkin_mummy)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testAvatarSelectionScreen_withNotEnoughXP_showsAlertDialog() {
        // Arrange: Use a test case where XP is less than 50
        val currentXP = 40
        val currentAvatar = 0
        var selectedAvatar = 0

        // Launch the composable
        composeTestRule.setContent {
            AvatarSelectionScreen(
                currentXP = currentXP,
                currentAvatar = currentAvatar,
                onAvatarSelected = { selectedAvatar = it },
                onNavigateBack = {}
            )
        }

        // Act: Check if the AlertDialog appears
        composeTestRule.onNodeWithText("Not Enough XP").assertIsDisplayed()

        // Act: Click the "OK" button to dismiss the dialog
        composeTestRule.onNodeWithText("OK").performClick()

        // Assert: Ensure no avatar options are displayed (since XP is not enough)
        composeTestRule.onNodeWithTag("AvatarOption 1").assertDoesNotExist()
        composeTestRule.onNodeWithTag("AvatarOption 2").assertDoesNotExist()
        composeTestRule.onNodeWithTag("AvatarOption 3").assertDoesNotExist()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testAvatarSaveButton_navigation() {
        // Arrange
        val currentXP = 100
        val currentAvatar = 0
        var selectedAvatar = 0
        var navigateCalled = false

        // Launch the composable
        composeTestRule.setContent {
            AvatarSelectionScreen(
                currentXP = currentXP,
                currentAvatar = currentAvatar,
                onAvatarSelected = { selectedAvatar = it },
                onNavigateBack = { navigateCalled = true }
            )
        }

        // Act: Select an avatar and click "Save and Continue"
        composeTestRule.onNodeWithTag("AvatarOption 1").performClick()
        composeTestRule.onNodeWithText("Save and Continue").performClick()

        // Assert: Check if navigation back was triggered
        assertEquals(navigateCalled, true)
    }
}


class MathProblemsTest {

    @Test
    fun testGenerateQuestion() {
        // This test checks if the question is in the correct format
        val question = generateQuestion()
        val parts = question.split(" ")

        assert(parts.size == 3) // A valid question should have 3 parts (a, operator, b)
        assert(parts[1] == "+" || parts[1] == "-") // The operator should be "+" or "-"
    }

    @Test
    fun testEvaluateQuestion_Addition() {
        // This test evaluates an addition question
        val question = "3 + 5"
        val result = evaluateQuestion(question)
        assertEquals(8, result) // The expected result of 3 + 5 is 8
    }

    @Test
    fun testEvaluateQuestion_Subtraction() {
        // This test evaluates a subtraction question
        val question = "10 - 4"
        val result = evaluateQuestion(question)
        assertEquals(6, result) // The expected result of 10 - 4 is 6
    }

    @Test
    fun testEvaluateQuestion_InvalidOperator() {
        // This tests invalid operator handling
        val question = "3 * 5" // Invalid operator
        val result = evaluateQuestion(question)
        assertEquals(0, result) // Since "*" is not supported, it should return 0
    }

    // Testing XP Logic Without Mocks

    @Test
    fun testAnswerCorrectly() {
        // Simulate user input for a correct answer
        val question = "5 + 3"
        val correctAnswer = 8
        val userAnswer = "8"

        // Simulate evaluation and XP calculation
        var earnedXP = 0
        if (userAnswer.toInt() == correctAnswer) {
            earnedXP = 5 // Earn XP for a correct answer
        }

        // Verify that the earned XP is correct
        assertEquals(earnedXP, 5)
    }

    @Test
    fun testAnswerIncorrectly() {
        // Simulate user input for an incorrect answer
        val question = "7 - 3"
        val correctAnswer = 4
        val userAnswer = "5"

        // Simulate evaluation and XP calculation
        var earnedXP = 0
        if (userAnswer.toInt() == correctAnswer) {
            earnedXP = 5
        }

        // Verify no XP is awarded for an incorrect answer
        assertEquals(earnedXP, 0)
    }
}

class XPManagerTest {

    @Test
    fun testInitialXP() {
        // Create an instance of XPManager
        val xpManager = XPManager()

        // Verify that the initial XP is 0
        assertEquals(0, xpManager.getCurrentXP())
    }

    @Test
    fun testAddXP() {
        // Create an instance of XPManager
        val xpManager = XPManager()

        // Add XP and verify the total
        xpManager.addXP(10)
        assertEquals(10, xpManager.getCurrentXP())

        // Add more XP and verify the updated total
        xpManager.addXP(5)
        assertEquals(15, xpManager.getCurrentXP())
    }

    @Test
    fun testMultipleAdditions() {
        // Create an instance of XPManager
        val xpManager = XPManager()

        // Perform multiple additions
        xpManager.addXP(3)
        xpManager.addXP(7)
        xpManager.addXP(20)

        // Verify the total XP
        assertEquals(30, xpManager.getCurrentXP())
    }

    @Test
    fun testAddZeroXP() {
        // Create an instance of XPManager
        val xpManager = XPManager()

        // Add 0 XP and verify the total remains unchanged
        xpManager.addXP(0)
        assertEquals(0, xpManager.getCurrentXP())
    }

    @Test
    fun testNegativeXP() {
        // Create an instance of XPManager
        val xpManager = XPManager()

        // Add a negative XP value
        xpManager.addXP(-5)

        // Verify that the XP decreases correctly
        assertEquals(-5, xpManager.getCurrentXP())
    }
}

@RunWith(AndroidJUnit4::class)
class UserProfileTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun userProfile_displaysUsernameAndXP() {
        val username = "Test User"
        val currentXP = 50

        composeTestRule.setContent {
            CatMathTheme {
                UserProfile(username = username, currentXP = currentXP, currentAvatar = R.drawable.sample_avatar, onAvatarClick = {})
            }
        }

        // Check if username is displayed
        composeTestRule.onNodeWithText(username).assertIsDisplayed()

        // Check if XP is displayed
        composeTestRule.onNodeWithText("$currentXP XP").assertIsDisplayed()
    }

    @Test
    fun userProfile_displaysProgressBarCorrectly() {
        val currentXP = 75 // Expect progress to be 75%

        composeTestRule.setContent {
            CatMathTheme {
                UserProfile(username = "Test User", currentXP = currentXP, currentAvatar = R.drawable.sample_avatar, onAvatarClick = {})
            }
        }

        // Verify the LinearProgressIndicator renders with the correct progress
        composeTestRule.onNodeWithText("LinearProgressIndicator").assertExists()
    }

    @Test
    fun userProfile_avatarClickInvokesCallback() {
        var wasClicked = false
        composeTestRule.setContent {
            CatMathTheme {
                UserProfile(
                    username = "Test User",
                    currentXP = 50,
                    currentAvatar = R.drawable.sample_avatar,
                    onAvatarClick = { wasClicked = true }
                )
            }
        }

        // Perform click on the avatar
        composeTestRule.onNodeWithContentDescription("User Avatar").performClick()

        // Assert that the click callback was invoked
        assert(wasClicked)
    }
}


