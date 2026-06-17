package com.example.passwordgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PasswordRule(
    val length: Int,
    val useNumbers: Boolean,
    val useSymbols: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeneratorGeselScreen()
                }
            }
        }

fun generirajGeslo(pravila: PasswordRule): String {
    val malecrke = "abcdefghijklmnopqrstuvwxyz"
    val velikecrke = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val stevilke = "0123456789"
    val simboli = "!@#\$%^&*"

    var znaki = malecrke + velikecrke
    if (pravila.useNumbers) znaki += stevilke
    if (pravila.useSymbols) znaki += simboli

    var geslo = ""
    repeat(pravila.length) {
        geslo += znaki.random()
    }
    return geslo
}

@Composable
fun GeneratorGeselScreen(){
    var dolzinagesla by remember {
        mutableStateOf(8f)
    }
    var stgeslo by remember {
        mutableStateOf(false)
    }
    var znakigeslo by remember {
        mutableStateOf(false)
    }
    var geslo by remember { mutableStateOf("") }


    // NI } TUKAJ
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Text("Generator gesel", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Z sliderjem izberite dolžino vašega gesla", fontSize = 15.sp)
        Slider(
            value = dolzinagesla,
            onValueChange = { dolzinagesla = it },
            valueRange = 8f..24f
        )
        Text("Dolžina: ${dolzinagesla.toInt()}")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ali želite da geslo vsebuje Številke    ", fontSize = 15.sp)
            Switch(
                checked = stgeslo,
                onCheckedChange = { stgeslo = it }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ali želite da geslo vsebuje Simbole    ", fontSize = 15.sp)
            Switch(
                checked = znakigeslo,
                onCheckedChange = { znakigeslo = it }
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val pravila = PasswordRule(
                        length = dolzinagesla.toInt(),
                        useNumbers = stgeslo,
                        useSymbols = znakigeslo
                    )
                    geslo = generirajGeslo(pravila)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Vaše geslo: $geslo")
            }
        }
        } // ← Column se zaključi tukaj
} // ← Funkcija se zaključi tukaj