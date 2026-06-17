package com.example.predavanje6ucenje

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// ---- MODEL ----
data class Artikel(
    val ime: String,
    val vKosarici: Boolean
)

// ---- PERSISTENCA ----
fun shraniSeznam(context: Context, seznam: List<Artikel>) {
    val prefs = context.getSharedPreferences("nakupovalni", Context.MODE_PRIVATE)
    val json = Gson().toJson(seznam)
    prefs.edit().putString("seznam", json).apply()
}

fun preberiSeznam(context: Context): List<Artikel> {
    val prefs = context.getSharedPreferences("nakupovalni", Context.MODE_PRIVATE)
    val json = prefs.getString("seznam", null) ?: return emptyList()
    val tip = object : TypeToken<List<Artikel>>() {}.type
    return Gson().fromJson(json, tip)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NakupovalniSeznam()
        }
    }
}

@Composable
fun NakupovalniSeznam(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // ob zagonu preberi shranjeni seznam z diska
    val seznam = remember {
        mutableStateListOf<Artikel>().apply { addAll(preberiSeznam(context)) }
    }
    var novArtikel by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nakupovalni seznam",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // vrstica za vnos novega artikla
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = novArtikel,
                onValueChange = { novArtikel = it },
                label = { Text("Nov artikel") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (novArtikel.isNotBlank()) {
                        seznam.add(Artikel(novArtikel, false))
                        novArtikel = ""
                        shraniSeznam(context, seznam)   // SHRANI ob dodajanju
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Dodaj")
            }
        }

        // seznam artiklov
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            itemsIndexed(seznam) { index, artikel ->
                ArtikelVrstica(
                    artikel = artikel,
                    onPreklop = { novoStanje ->
                        seznam[index] = artikel.copy(vKosarici = novoStanje)
                        shraniSeznam(context, seznam)   // SHRANI ob preklopu
                    }
                )
            }
        }
    }
}

@Composable
fun ArtikelVrstica(
    artikel: Artikel,
    onPreklop: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = artikel.vKosarici,
            onCheckedChange = onPreklop
        )
        Text(text = artikel.ime, fontSize = 18.sp)
    }
}