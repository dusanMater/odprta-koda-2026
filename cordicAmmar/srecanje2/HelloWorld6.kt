package com.example.helloworld6

import android.os.Bundle
import android.view.animation.GridLayoutAnimationController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld6.ui.theme.HelloWorld6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Glava()
            // GlavniDel()
            // Noga()
        }
    }
}

@Composable
fun Glava() {
    Column(modifier = Modifier.padding(
        start = 30.dp,
        top = 30.dp
    )) {
        Text("Prva vaja za Jetpack Compose", Modifier.padding(
            bottom = 50.dp
        ))
        Text("Alma Mater")
        Text("Informacijske tehnologije")
    }
}

@Composable
fun GlavniDel() {
    Text("Spoznavamo funkcije Composable")
}

