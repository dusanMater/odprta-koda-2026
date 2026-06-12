package com.example.predavanje6vaja2

import android.R.attr.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje6vaja2.ui.theme.Predavanje6vaja2Theme

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

    Column() {


        Spacer(modifier = Modifier.height(45.dp))


        Image(
            painter = painterResource(R.drawable.glava),
            contentDescription = "glava aplikacije",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )
        Naslov("Jetpack Compose tutorial")
        Besedilo("Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
                modifier = Modifier.padding(horizontal = 16.dp))


        Spacer(modifier = Modifier.height(12.dp))


        Besedilo("In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
fun Naslov(naslov: String){
    Text(
        text = "$naslov",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )
}
@Composable
fun Besedilo(besedilo: String, modifier: Modifier = Modifier){
    Text(
        text = "$besedilo",
        textAlign = TextAlign.Justify,
        modifier = modifier
    )
}