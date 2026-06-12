package com.example.helloworld7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 56.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Glava()
                //GlavniDel()
                //Noga()
            }
        }
    }
}

@Composable
fun Glava()
{
    Column(
        modifier = Modifier.padding(
            start = 30.dp,
            top = 50.dp)
    )
    {
        Text( text =  "Prva vaja za Jetpack Compose", Modifier.padding(bottom = 50.dp), fontSize = 120.sp) // lambda funkcija - anonimne funkcije // če te zanima več, preberi !!!! doma !!!1
        Text("Alma Mater", fontSize = 72.sp)
        Text("informacijske tehnlogije", fontSize = 48.sp)
    }

}

@Composable
fun GlavniDel()
{
    Text("Spoznavamo funkcije Composable!", fontSize = 84.sp)
}