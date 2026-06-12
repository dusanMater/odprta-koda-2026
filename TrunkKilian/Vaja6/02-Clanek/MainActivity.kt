package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Clanek()
        }
    }
}

@Composable
fun Clanek() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.glava),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Jetpack Compose tutorial",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Odstavek(
            besedilo = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Odstavek(
            besedilo = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Odstavek(
    besedilo: String,
    modifier: Modifier
) {
    Text(
        text = besedilo,
        textAlign = TextAlign.Justify,
        fontSize = 16.sp,
        modifier = modifier
    )
}
