package com.example.ucenje
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ...

// 🗂️ DATA CLASS — pred MainActivity, "globalna" definicija
data class Artikel(
    val naziv: String,
    var vKosarici: Boolean = false
)

// 🏠 MAIN ACTIVITY — vstopna točka aplikacije, ena sama
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NakupovalniSeznamScreen() // ← pokličemo našo UI funkcijo
        }
    }
}

// 🎨 COMPOSABLE FUNKCIJE — pod MainActivity, toliko jih hočeš
@Composable
fun NakupovalniSeznamScreen() {
    var vnos by remember { mutableStateOf("") }
    val seznam = remember { mutableStateListOf<Artikel>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nakupovalni seznam",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = vnos,
            onValueChange = { vnos = it },
            label = { Text("Nov artikel") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (vnos.isNotBlank()) {
                    seznam.add(Artikel(naziv = vnos))
                    vnos = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dodaj")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(seznam) { artikel ->
                var urejanje by remember { mutableStateOf(false) }
                var novNaziv by remember { mutableStateOf(artikel.naziv) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = artikel.vKosarici,
                        onCheckedChange = {
                            val index = seznam.indexOf(artikel)
                            seznam[index] = artikel.copy(vKosarici = it)
                        }
                    )

                    if (urejanje) {
                        OutlinedTextField(
                            value = novNaziv,
                            onValueChange = { novNaziv = it },
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = {
                            val index = seznam.indexOf(artikel)
                            seznam[index] = artikel.copy(naziv = novNaziv)
                            urejanje = false
                        }) {
                            Text("✓")
                        }
                    } else {
                        Text(
                            text = artikel.naziv,
                            modifier = Modifier
                                .weight(1f)
                                .clickable { urejanje = true }
                        )
                        Button(onClick = { seznam.remove(artikel) }) {
                            Text("X")
                        }
                    }
                }
            }
            }
        }
    }