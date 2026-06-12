package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.TextStyle
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalkulator()
        }
    }
}

// Paleta — notepad rumeno ozadje + črni primary
private val barvaOzadja = Color(0xFFFAF3CA)
private val barvaPrimarna = Color.Black

private val operacije = listOf("+", "-", "*", "/", "%")

@Composable
fun Kalkulator() {
    var prvoStevilo by remember { mutableStateOf("") }
    var drugoStevilo by remember { mutableStateOf("") }
    var izbranaOperacija by remember { mutableStateOf<String?>(null) }
    var rezultat by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(barvaOzadja)
            .padding(start = 20.dp, end = 20.dp, top = 100.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Prvo število
        OutlinedTextField(
            value = prvoStevilo,
            onValueChange = { prvoStevilo = it },
            label = { Text("Prvo število", fontSize = 32.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(fontSize = 44.sp, color = barvaPrimarna),
            colors = poljeBarve(),
            modifier = Modifier.fillMaxWidth()
        )

        // Drugo število
        OutlinedTextField(
            value = drugoStevilo,
            onValueChange = { drugoStevilo = it },
            label = { Text("Drugo število", fontSize = 32.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            textStyle = TextStyle(fontSize = 44.sp, color = barvaPrimarna),
            colors = poljeBarve(),
            modifier = Modifier.fillMaxWidth()
        )

        // Izberi operacijo + radio gumbi (subskupina)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Text(
                text = "Izberi operacijo:",
                color = barvaPrimarna,
                fontSize = 44.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                operacije.forEach { op ->
                    OperacijaRadio(
                        label = op,
                        izbrana = (izbranaOperacija == op),
                        onIzbira = { izbranaOperacija = op }
                    )
                }
            }
        }

        // Izračunaj + Počisti (dodatek)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 84.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    rezultat = izracunaj(prvoStevilo, drugoStevilo, izbranaOperacija)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = barvaPrimarna,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 18.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Izračunaj", fontSize = 32.sp)
            }
            Button(
                onClick = {
                    prvoStevilo = ""
                    drugoStevilo = ""
                    izbranaOperacija = null
                    rezultat = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = barvaPrimarna,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 18.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("Počisti", fontSize = 32.sp)
            }
        }

        // Prikaz rezultata (samo ko je nastavljen)
        rezultat?.let {
            Text(
                text = it,
                color = barvaPrimarna,
                fontSize = 48.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 184.dp)
            )
        }
    }
}

@Composable
private fun OperacijaRadio(label: String, izbrana: Boolean, onIzbira: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = izbrana,
            onClick = onIzbira,
            colors = RadioButtonDefaults.colors(
                selectedColor = barvaPrimarna,
                unselectedColor = barvaPrimarna
            ),
            modifier = Modifier.scale(1.8f)
        )
        Text(text = label, color = barvaPrimarna, fontSize = 44.sp)
    }
}

@Composable
private fun poljeBarve() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = barvaPrimarna,
    unfocusedBorderColor = barvaPrimarna,
    focusedLabelColor = barvaPrimarna,
    unfocusedLabelColor = barvaPrimarna,
    cursorColor = barvaPrimarna,
    focusedTextColor = barvaPrimarna,
    unfocusedTextColor = barvaPrimarna
)

// Glavna računska logika
private fun izracunaj(a: String, b: String, op: String?): String {
    // Sprejmi "," ali "." za decimalko (slovenska navada)
    val x = a.replace(",", ".").toDoubleOrNull()
    val y = b.replace(",", ".").toDoubleOrNull()
    if (x == null || y == null || op == null) {
        return "Vnesi obe števili in izberi operacijo"
    }
    return when (op) {
        "+" -> "Rezultat: ${formatRez(x + y)}"
        "-" -> "Rezultat: ${formatRez(x - y)}"
        "*" -> "Rezultat: ${formatRez(x * y)}"
        "/" -> if (y == 0.0) "Napaka: deljenje z 0" else "Rezultat: ${formatRez(x / y)}"
        "%" -> if (y == 0.0) "Napaka: deljenje z 0" else "Rezultat: ${formatRez(x % y)}"
        else -> ""
    }
}

// Pametno formatiranje: celo število brez decimalk, sicer decimalka
private fun formatRez(d: Double): String {
    return if (d == d.toLong().toDouble()) d.toLong().toString() else d.toString()
}
