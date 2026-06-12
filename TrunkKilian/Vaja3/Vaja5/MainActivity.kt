package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kalkulator()
        }
    }
}

@Composable
fun Kalkulator() {
    var prvoStevilo by remember { mutableStateOf("") }
    var drugoStevilo by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        PoljeZaVnos(
            vrednost = prvoStevilo,
            placeholder = "Prvo število",
            onValueChange = { prvoStevilo = it }
        )
        PoljeZaVnos(
            vrednost = drugoStevilo,
            placeholder = "Drugo število",
            onValueChange = { drugoStevilo = it }
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OperacijaGumb("+") {
                rezultat = izracunaj(prvoStevilo, drugoStevilo, "+")
            }
            OperacijaGumb("-") {
                rezultat = izracunaj(prvoStevilo, drugoStevilo, "-")
            }
            OperacijaGumb("*") {
                rezultat = izracunaj(prvoStevilo, drugoStevilo, "*")
            }
            OperacijaGumb("/") {
                rezultat = izracunaj(prvoStevilo, drugoStevilo, "/")
            }
            OperacijaGumb("%") {
                rezultat = izracunaj(prvoStevilo, drugoStevilo, "%")
            }
        }
        Text(
            text = rezultat,
            color = Color.Black,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
    }
}

@Composable
fun PoljeZaVnos(
    vrednost: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = vrednost,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        decorationBox = { innerTextField ->
            if (vrednost.isEmpty()) {
                Text(
                    text = placeholder,
                    color = Color(0xFF57515D),
                    fontSize = 18.sp
                )
            }
            innerTextField()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Gray)
            .padding(start = 16.dp, top = 22.dp, end = 16.dp)
    )
}

@Composable
fun RowScope.OperacijaGumb(
    znak: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6D51B5),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .weight(1f)
            .height(46.dp)
            .padding(horizontal = 1.dp)
    ) {
        Text(
            text = znak,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

fun izracunaj(
    prvoStevilo: String,
    drugoStevilo: String,
    operacija: String
): String {
    val prvo = prvoStevilo.toDoubleOrNull() ?: return ""
    val drugo = drugoStevilo.toDoubleOrNull() ?: return ""

    val rezultat = when (operacija) {
        "+" -> prvo + drugo
        "-" -> prvo - drugo
        "*" -> prvo * drugo
        "/" -> if (drugo == 0.0) return "Deljenje z 0 ni dovoljeno" else prvo / drugo
        "%" -> if (drugo == 0.0) return "Deljenje z 0 ni dovoljeno" else prvo % drugo
        else -> return ""
    }

    return "Rezultat: $rezultat"
}
