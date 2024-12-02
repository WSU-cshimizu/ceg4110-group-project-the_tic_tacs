import android.content.Context
import kotlin.random.Random

fun getRandomCatFact(context: Context): String {
    return try {
        // Open the catfacts.txt file from the assets folder
        val inputStream = context.assets.open("catfacts.txt")
        val facts = inputStream.bufferedReader().use { it.readLines() }
        if (facts.isNotEmpty()) {
            facts[Random.nextInt(facts.size)]
        } else {
            "Did you know? Cats are amazing!"
        }
    } catch (e: Exception) {
        "Did you know? Cats are amazing!"
    }
}
