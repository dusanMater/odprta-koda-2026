package com.example.domacanaloga3_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domacanaloga3_4.ui.theme.Domacanaloga34Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Domacanaloga34Theme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    kalkulator()
                }
            }
        }
    }
}

@Composable
fun kalkulator() {
    var vnosEna by remember { mutableStateOf("") }
    var vnosDva by remember { mutableStateOf("") }
    var izbranaOperacija by remember { mutableStateOf("+") }
    var izpisRezultata by remember { mutableStateOf("") }
    val vseOperacije = listOf("+", "-", "*", "/", "%")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Spacer(modifier =  Modifier.height(30.dp))

        Text(
            text = "Kalkulator",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = vnosEna,
            onValueChange = { vnosEna = it },
            label = { Text("Prvo število") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vnosDva,
            onValueChange = { vnosDva = it },
            label = { Text("Drugo število") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Izberi operacijo:",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            vseOperacije.forEach { operacija ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                        .clickable { izbranaOperacija = operacija }
                ) {
                    RadioButton(
                        selected = (izbranaOperacija == operacija),
                        onClick = { izbranaOperacija = operacija }
                    )
                    Text(
                        text = operacija,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = {
                val n1 = vnosEna.toDoubleOrNull()
                val n2 = vnosDva.toDoubleOrNull()

                if (n1 == null || n2 == null) {
                    izpisRezultata = "Vpisi obe stevilki!"
                } else {
                    val racun = when (izbranaOperacija) {
                        "+" -> n1 + n2
                        "-" -> n1 - n2
                        "*" -> n1 * n2
                        "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
                        "%" -> if (n2 != 0.0) n1 % n2 else Double.NaN
                        else -> 0.0
                    }

                    izpisRezultata = if (racun.isNaN()) {
                        "Prisotno deljenje z 0!"
                    } else if (racun % 1 == 0.0) {
                        racun.toInt().toString()
                    } else {
                        racun.toString()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(text = "Izračunaj", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(Color(0xFFF3EDF7), shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = if (izpisRezultata.isEmpty()) "Rezultat: " else "Rezultat: $izpisRezultata",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}