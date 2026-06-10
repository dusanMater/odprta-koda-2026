package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.core.text.HtmlCompat
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.am04.model.TriviaQuestion
import com.example.am04.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviaApp()
        }
    }
}

@Composable
fun TriviaApp() {
    var podatki by remember { mutableStateOf(listOf<TriviaQuestion>()) }
    val scope = rememberCoroutineScope()
    val soPodatkiPrazni = podatki.isEmpty()
    var seNalaga by remember { mutableStateOf(false) }
    var napaka by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Button(
            onClick = {
                seNalaga = true
                napaka = ""
                scope.launch {
                    try {
                        val response = RetrofitInstance.api.getQuestions(10, 12, "easy", "multiple")
                        podatki = response.results
                    } catch (e: Exception) {
                        napaka = "Napaka pri nalaganju podatkov: " + e
                    }

                    seNalaga = false
                }
            },
            enabled = !seNalaga,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(
                text = if (seNalaga) {
                    "Nalagam..."
                } else if (soPodatkiPrazni) {
                    "Naloži vprašanja"
                } else {
                    "Zamenjaj vprašanja"
                }
            )
        }

        if (seNalaga) {
            Text(
                text = "Podatki se nalagajo...",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else if (napaka != "") {
            Text(
                text = napaka,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else if (soPodatkiPrazni) {
            Text(
                text = "Trenutno ni podatkov. Klikni zgornji gumb.",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else {
            for (podatek in podatki) {
                Text(
                    text = decode(podatek.question),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "+ " + decode(podatek.correct_answer),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                for (napacenOdgovor in podatek.incorrect_answers) {
                    Text(
                        text = "- " + decode(napacenOdgovor),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}

fun decode(text: String): String {
    return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}
