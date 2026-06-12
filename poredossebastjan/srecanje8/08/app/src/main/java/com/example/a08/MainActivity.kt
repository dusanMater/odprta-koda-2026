package com.example.a08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a08.model.KvizResponse
import com.example.a08.model.Question
import com.example.a08.network.KvizApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var vprasanje by remember {
                mutableStateOf<List<Question>>(emptyList())
            }

            LaunchedEffect(Unit) {

                val result: KvizResponse =
                    RetrofitInstance.api.getQuestions()

                vprasanje = result.results
            }

            Kviz(vprasanje)


        }
    }
}

@Composable
fun Kviz(
    vprasanje: List<Question>
) {

    LazyColumn {

        items(vprasanje) { question ->

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(text = question.question)

                Text(text = question.correct_answer)

                question.incorrect_answers.forEach {
                    Text(text = it)
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


object RetrofitInstance {

    private const val BASE_URL = "https://opentdb.com/"

    val api: KvizApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(KvizApi::class.java)
    }
}