package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
            NapredniKalkulator()
        }
    }
}

@Composable
fun NapredniKalkulator() {
    var prvoStevilo by remember { mutableStateOf("23") }
    var drugoStevilo by remember { mutableStateOf("12") }
    var operacija by remember { mutableStateOf("*") }
    var rezultat by remember { mutableStateOf("Rezultat: 276.0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(start = 18.dp, top = 22.dp, end = 18.dp)
    ) {
        StevilskoPolje(
            vrednost = prvoStevilo,
            label = "Prvo število",
            onValueChange = { prvoStevilo = it }
        )
        StevilskoPolje(
            vrednost = drugoStevilo,
            label = "Drugo število",
            onValueChange = { drugoStevilo = it }
        )
        Text(
            text = "Izberi operacijo:",
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 18.dp, bottom = 14.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OperacijaIzbira("+", operacija) { operacija = it }
            OperacijaIzbira("-", operacija) { operacija = it }
            OperacijaIzbira("*", operacija) { operacija = it }
            OperacijaIzbira("/", operacija) { operacija = it }
            OperacijaIzbira("%", operacija) { operacija = it }
        }
        Button(
            onClick = {
                rezultat = izracunaj(prvoStevilo, drugoStevilo, operacija)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7053B5),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(top = 18.dp)
        ) {
            Text(
                text = "Izračunaj",
                fontSize = 16.sp
            )
        }
        Text(
            text = rezultat,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 22.dp)
        )
    }
}

@Composable
fun StevilskoPolje(
    vrednost: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = vrednost,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF7053B5),
            focusedLabelColor = Color(0xFF7053B5),
            cursorColor = Color(0xFF7053B5)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}

@Composable
fun RowScope.OperacijaIzbira(
    znak: String,
    izbranaOperacija: String,
    onIzberi: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(54.dp)
            .weight(1f)
            .border(1.dp, Color.Gray, RoundedCornerShape(3.dp))
            .clickable { onIzberi(znak) }
    ) {
        RadioButton(
            selected = izbranaOperacija == znak,
            onClick = { onIzberi(znak) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF7053B5)
            )
        )
        Text(
            text = znak,
            color = Color.Black
        )
    }
}

fun izracunaj(
    prvoStevilo: String,
    drugoStevilo: String,
    operacija: String
): String {
    val prvo = prvoStevilo.toDoubleOrNull() ?: return "Rezultat:"
    val drugo = drugoStevilo.toDoubleOrNull() ?: return "Rezultat:"

    val rezultat = when (operacija) {
        "+" -> prvo + drugo
        "-" -> prvo - drugo
        "*" -> prvo * drugo
        "/" -> if (drugo == 0.0) return "Rezultat: deljenje z 0 ni dovoljeno" else prvo / drugo
        "%" -> if (drugo == 0.0) return "Rezultat: deljenje z 0 ni dovoljeno" else prvo % drugo
        else -> return "Rezultat:"
    }

    return "Rezultat: $rezultat"
}
