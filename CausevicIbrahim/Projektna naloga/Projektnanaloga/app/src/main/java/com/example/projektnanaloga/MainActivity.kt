package com.example.projektnanaloga

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                GameDealerApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealerApp() {
    var gamerMode by remember { mutableStateOf(false) }

    if (!gamerMode) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "🎮GAMEDEALER🎮",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Najboljše ponudbe iger 💸",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Gamer mode",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Switch(
                        checked = gamerMode,
                        onCheckedChange = { gamerMode = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFFFF4A4A),
                            checkedTrackColor = Color(0xFFFF4A4A).copy(alpha = 0.5f),
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "🔓 Vklopi Gamer mode za dostop do vseh funkcij",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    } else {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        var vpisanoBesedilo by remember { mutableStateOf("") }
        var samoSteam by remember { mutableStateOf(false) }
        val rezultatiIskanja = remember { mutableStateListOf<Igra>() }
        val mojSeznamZelja = remember { mutableStateListOf<Igra>() }
        var nalaganje by remember { mutableStateOf(false) }

        val neonModra = Color(0xFF00E5FF)
        val neonRdeca = Color(0xFFFF4A4A)
        val temnoSiva = Color(0xFF1A1A2E)
        val svetloSiva = Color(0xFF16213E)
        val bela = Color.White
        val siva = Color(0xFF888888)
        val oranzna = Color(0xFFFFA500)

        LaunchedEffect(Unit) {
            val nalozeneIgre = naloziIgre(context)
            mojSeznamZelja.clear()
            mojSeznamZelja.addAll(nalozeneIgre)
        }

        val rezervnaMapaTrgovin = mapOf(
            "1" to "Steam", "2" to "GamersGate", "3" to "GreenManGaming",
            "4" to "Amazon", "5" to "GameStop", "6" to "Origin",
            "7" to "GOG", "8" to "Direct2Drive", "11" to "Humble Store",
            "13" to "Ubisoft Store", "15" to "Fanatical", "23" to "GameBillet",
            "24" to "Voidu", "25" to "Epic Games", "27" to "Gamesplanet",
            "31" to "Microsoft Store", "35" to "IndieGala"
        )
        var mapaTrgovin by remember { mutableStateOf<Map<String, String>?>(null) }
        fun vrniImeTrgovine(storeID: String): String {
            mapaTrgovin?.get(storeID)?.let { return it }
            return rezervnaMapaTrgovin[storeID] ?: "Trgovina #$storeID"
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Ozadje",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.65f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "⬅️ Izklopi Gamer mode",
                    color = neonModra,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { gamerMode = false }
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "🎮 GAMEDEALER 🎮",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = neonModra,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))

                OutlinedTextField(
                    value = vpisanoBesedilo,
                    onValueChange = { vpisanoBesedilo = it },
                    label = { Text("🔍 Išči igre...", color = siva) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = neonRdeca,
                        unfocusedBorderColor = siva,
                        focusedLabelColor = neonRdeca,
                        cursorColor = neonRdeca,
                        focusedTextColor = bela,
                        unfocusedTextColor = bela
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("🎮 Samo Steam igre", color = bela, fontSize = 16.sp)
                    Switch(
                        checked = samoSteam,
                        onCheckedChange = { samoSteam = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = neonRdeca)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        if (vpisanoBesedilo.trim().isNotEmpty()) {
                            nalaganje = true
                            coroutineScope.launch {
                                try {
                                    if (mapaTrgovin == null) {
                                        try {
                                            val trgovine = RetrofitClient.api.dohvatiTrgovine()
                                            mapaTrgovin = trgovine.associate { it.storeID to it.storeName }
                                        } catch (e: Exception) {
                                            mapaTrgovin = emptyMap()
                                        }
                                    }
                                    val zadetki = RetrofitClient.api.poisciIgre(vpisanoBesedilo.trim())
                                    rezultatiIskanja.clear()
                                    for (igra in zadetki) {
                                        var imeTrgovine = "Neznano"
                                        var urlPonudbe = ""
                                        var koncnaCena = "${igra.cheapest}€"
                                        try {
                                            val pogled = RetrofitClient.api.pridobiIgra(igra.gameID)
                                            val ponudbe = pogled.deals ?: emptyList()
                                            if (ponudbe.isNotEmpty()) {
                                                val najcenejsa = ponudbe.minByOrNull {
                                                    it.price?.toDoubleOrNull() ?: Double.MAX_VALUE
                                                }
                                                if (najcenejsa != null) {
                                                    imeTrgovine = vrniImeTrgovine(najcenejsa.storeID ?: "")
                                                    urlPonudbe = "https://www.cheapshark.com/redirect?dealID=${najcenejsa.dealID}"
                                                    najcenejsa.price?.let { koncnaCena = "$it€" }
                                                }
                                            }
                                        } catch (e: Exception) {
                                            urlPonudbe = "https://www.cheapshark.com/redirect?dealID=${igra.gameID}"
                                        }
                                        if (samoSteam && imeTrgovine != "Steam") {
                                            continue
                                        }
                                        rezultatiIskanja.add(
                                            Igra(
                                                id = igra.gameID,
                                                naslov = igra.external,
                                                cena = koncnaCena,
                                                slikaUrl = igra.thumb,
                                                store = imeTrgovine,
                                                dealUrl = urlPonudbe
                                            )
                                        )
                                    }
                                    if (rezultatiIskanja.isEmpty()) {
                                        Toast.makeText(context, "❌ Ni najdenih iger.", Toast.LENGTH_SHORT).show()
                                    }
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Napaka: ${e.message}", Toast.LENGTH_LONG).show()
                                } finally {
                                    nalaganje = false
                                }
                            }
                        } else {
                            Toast.makeText(context, "✏️ Vpiši iskalni niz!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = neonModra)
                ) {
                    if (nalaganje) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = temnoSiva)
                    } else {
                        Text("POIŠČI NA SPLETU ⚡", color = temnoSiva, fontWeight = FontWeight.Bold)
                    }
                }

                if (rezultatiIskanja.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "REZULTATI:",
                        color = neonRdeca,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    rezultatiIskanja.forEach { igra ->
                        KarticaIgre(
                            igra = igra,
                            besediloGumba = "☑ Dodaj",
                            barvaGumba = neonRdeca,
                            temnaKartica = svetloSiva,
                            obKliku = {
                                if (!mojSeznamZelja.any { it.id == igra.id }) {
                                    mojSeznamZelja.add(igra)
                                    Toast.makeText(context, "✅ Dodano na seznam želja!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "⚠️ Igra je že v seznamu!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "MOJ SEZNAM IGER 🎮",
                    color = oranzna,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                if (mojSeznamZelja.isEmpty()) {
                    Text(
                        text = "Seznam je prazen. Dodaj igre z gumbom 'Dodaj'!",
                        color = siva,
                        modifier = Modifier.padding(12.dp)
                    )
                } else {
                    mojSeznamZelja.forEach { igra ->
                        KarticaIgre(
                            igra = igra,
                            besediloGumba = "Odstrani",
                            barvaGumba = neonRdeca,
                            temnaKartica = svetloSiva,
                            obKliku = {
                                mojSeznamZelja.remove(igra)
                                Toast.makeText(context, "🗑️ Odstranjeno iz seznama!", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            shraniIgre(context, mojSeznamZelja)
                            Toast.makeText(context, "Seznam uspešno shranjen!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = neonRdeca)
                    ) {
                        Text("SHRANI SEZNAM NA NAPRAVO", color = temnoSiva, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
fun KarticaIgre(
    igra: Igra,
    besediloGumba: String,
    barvaGumba: Color,
    temnaKartica: Color,
    obKliku: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = temnaKartica)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = igra.slikaUrl,
                contentDescription = "Slika igre",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(igra.naslov, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cena: ${igra.cena}", color = Color(0xFFFF4A4A), fontSize = 13.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Trgovina: ${igra.store}", color = Color.LightGray, fontSize = 11.sp)
                if (igra.dealUrl.isNotEmpty()) {
                    Text(
                        text = "🔗 Odpri ponudbo",
                        color = Color(0xFF00E5FF),
                        fontSize = 11.sp,
                        modifier = Modifier.clickable {
                            try {
                                val namen = Intent(Intent.ACTION_VIEW, Uri.parse(igra.dealUrl))
                                context.startActivity(namen)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Ne morem odpreti povezave", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
            Button(
                onClick = obKliku,
                colors = ButtonDefaults.buttonColors(containerColor = barvaGumba),
                modifier = Modifier.height(36.dp)
            ) {
                Text(besediloGumba, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}