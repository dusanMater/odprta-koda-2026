package com.example.triviaapp

import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.example.triviaapp.model.QuestionsResponse
import com.example.triviaapp.network.TriviaApi
import com.example.triviaapp.ui.theme.TriviaAppTheme
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen()
        }
    }
}

@Composable
fun Screen() {
    var newGame by remember { mutableStateOf(true) }
    var score by remember { mutableStateOf(0) }
    var questionsData by remember { mutableStateOf<QuestionsResponse?>(null) }

    var currentQuestion by remember { mutableStateOf(0) }

    val possibleAnswers = remember(currentQuestion, questionsData) {
        (
            (questionsData?.results?.get(currentQuestion)?.incorrect_answers ?: emptyList())
                    + (questionsData?.results?.get(currentQuestion)?.correct_answer ?: "")
            ).shuffled()
    }

    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var answered by remember { mutableStateOf(false) }

    LaunchedEffect(newGame) {
        if (newGame) {
            val response = RetrofitInstance.api.getQuestions(
                amount = 10,
                category = 18,
                difficulty = "medium",
                type = "multiple"
            )
            questionsData = response

            newGame = false
        }
    }

    Column (
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(top = 12.dp)
    ) {
        Text(
            text = "Score: $score",
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp
        )

        Text(
            text = "Question ${currentQuestion + 1}",
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp
        )

        Text(
            text = decodeHtml(questionsData?.results?.get(currentQuestion)?.question ?: "Loading..."),
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )

        Column (
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            possibleAnswers.forEach {answer ->
                val correctAnswer = questionsData?.results?.get(currentQuestion)?.correct_answer
                val buttonColor = when {
                    !answered ->
                        ButtonDefaults.buttonColors()

                    answer == correctAnswer ->
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Green
                        )

                    answer == selectedAnswer ->
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )

                    else ->
                        ButtonDefaults.buttonColors()
                }

                Button(
                    onClick = {
                        if (answered) return@Button

                        selectedAnswer = answer
                        answered = true

                        var correct: Boolean = answer == correctAnswer

                        Handler().postDelayed({
                            if (correct) {
                                score++
                            }
                            if (currentQuestion < 9) {
                                currentQuestion++
                            } else {
                                newGame = true
                                currentQuestion = 0
                            }

                            selectedAnswer = null
                            answered = false

                        }, 3000)
                    },
                    // enabled = !answered,
                    colors = buttonColor,
                    modifier = Modifier
                        .padding(8.dp)

                ) {
                    Text(text = decodeHtml(answer))
                }
            }

        }
    }
}

fun decodeHtml(text: String): String {
    return HtmlCompat.fromHtml(
        text,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    ).toString()
}

object RetrofitInstance {
    val api: TriviaApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TriviaApi::class.java)
    }
}