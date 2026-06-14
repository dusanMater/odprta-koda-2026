package com.example.izpitna

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var temniNacin by remember { mutableStateOf(false) }
            MaterialTheme(
                colorScheme = if (temniNacin) darkColorScheme() else lightColorScheme()
            ) {
                Aplikacija(temniNacin = temniNacin, onTemniNacinChange = { temniNacin = it })
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Aplikacija(temniNacin: Boolean, onTemniNacinChange: (Boolean) -> Unit) {
        val kon = LocalContext.current
        var iskalniNiz by remember { mutableStateOf("") }
        var rezultati by remember { mutableStateOf<List<Bencinska>>(emptyList()) }
        var priljubljene by remember { mutableStateOf(naloziPriljubljene(kon)) }
        var nalaganje by remember { mutableStateOf(false) }
        var osvezevanjeVseh by remember { mutableStateOf(false) }
        var iskanjeIzvedeno by remember { mutableStateOf(false) }
        val obseg = rememberCoroutineScope()
        var prikaziDialogNajcenejsa by remember { mutableStateOf(false) }
        var najcenejsaDrzava by remember { mutableStateOf<Bencinska?>(null) }

        LaunchedEffect(Unit) {
            priljubljene = naloziPriljubljene(kon)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocalGasStation, null, Modifier.size(32.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Cene goriva po državah")
                        }
                    }
                )
            }
        ) { notranjiRob ->
            Column(
                Modifier
                    .padding(notranjiRob)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = iskalniNiz,
                    onValueChange = {
                        iskalniNiz = it
                        if (iskanjeIzvedeno) iskanjeIzvedeno = false
                    },
                    label = { Text("Ime države (npr. Slovenia)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (iskalniNiz.isNotBlank()) {
                            iskanjeIzvedeno = true
                            obseg.launch {
                                nalaganje = true
                                rezultati = dobiCene(iskalniNiz)
                                nalaganje = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = iskalniNiz.isNotBlank()
                ) {
                    Text("Poišči cene")
                }

                if (nalaganje) {
                    Box(Modifier.fillMaxWidth(), Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                if (iskanjeIzvedeno && !nalaganje) {
                    if (rezultati.isNotEmpty()) {
                        Text("Zadetki:", style = MaterialTheme.typography.titleMedium)
                        LazyColumn {
                            items(rezultati) { drzava ->
                                Zadetek(
                                    podatki = drzava,
                                    obShrani = {
                                        val zeObstaja = priljubljene.any { it.koda == drzava.koda }
                                        if (zeObstaja) {
                                            Toast.makeText(kon, "${drzava.ime} je že shranjena!", Toast.LENGTH_SHORT).show()
                                        } else {
                                            priljubljene = priljubljene + Shranjena(
                                                drzava.ime, drzava.koda, drzava.valuta, drzava.enota,
                                                drzava.cenaDizla, drzava.cenaBencina
                                            )
                                            shraniPriljubljene(kon, priljubljene)
                                            Toast.makeText(kon, "${drzava.ime} shranjena", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                )
                                Spacer(Modifier.height(4.dp))
                            }
                        }
                    } else {
                        Box(Modifier.fillMaxWidth(), Alignment.Center) {
                            Text("Država ni najdena", color = MaterialTheme.colorScheme.error)
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Temni način")
                        Spacer(Modifier.width(8.dp))
                        Switch(checked = temniNacin, onCheckedChange = onTemniNacinChange)
                    }
                    Button(
                        onClick = {
                            obseg.launch {
                                najcenejsaDrzava = najcenejsaDrzavaDizel()
                                prikaziDialogNajcenejsa = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Najcenejši dizel")
                    }
                }

                Spacer(Modifier.height(8.dp))

                Column(Modifier.weight(1f)) {
                    Text("Priljubljene države:", style = MaterialTheme.typography.titleMedium)
                    if (priljubljene.isEmpty()) {
                        Box(Modifier.fillMaxWidth(), Alignment.Center) { Text("Ni shranjenih") }
                    } else {
                        LazyColumn {
                            items(priljubljene) { shran ->
                                Priljubljena(
                                    podatki = shran,
                                    obOdstrani = {
                                        priljubljene = priljubljene - shran
                                        shraniPriljubljene(kon, priljubljene)
                                        Toast.makeText(kon, "${shran.ime} odstranjena", Toast.LENGTH_SHORT).show()
                                    },
                                    obOsvezi = {
                                        obseg.launch {
                                            val vse = dobiVse()
                                            val posodobljena = vse.find { it.koda == shran.koda }
                                            if (posodobljena != null) {
                                                priljubljene = priljubljene.map {
                                                    if (it.koda == shran.koda) it.copy(
                                                        cenaDizla = posodobljena.cenaDizla,
                                                        cenaBencina = posodobljena.cenaBencina,
                                                        valuta = posodobljena.valuta,
                                                        enota = posodobljena.enota
                                                    ) else it
                                                }
                                                shraniPriljubljene(kon, priljubljene)
                                                Toast.makeText(kon, "${shran.ime} osvežena", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                )
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            if (priljubljene.isNotEmpty()) {
                                osvezevanjeVseh = true
                                obseg.launch {
                                    val vse = dobiVse()
                                    val posodobljene = priljubljene.map { shran ->
                                        vse.find { it.koda == shran.koda }?.let {
                                            shran.copy(
                                                cenaDizla = it.cenaDizla,
                                                cenaBencina = it.cenaBencina,
                                                valuta = it.valuta,
                                                enota = it.enota
                                            )
                                        } ?: shran
                                    }
                                    priljubljene = posodobljene
                                    shraniPriljubljene(kon, posodobljene)
                                    osvezevanjeVseh = false
                                    Toast.makeText(kon, "Vse države osvežene", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        enabled = priljubljene.isNotEmpty() && !osvezevanjeVseh
                    ) {
                        if (osvezevanjeVseh) {
                            Row {
                                CircularProgressIndicator(Modifier.size(20.dp), strokeWidth = 2.dp)
                                Spacer(Modifier.width(4.dp))
                                Text("Osvežujem...")
                            }
                        } else Text("Osveži vse")
                    }

                    Button(
                        onClick = {
                            priljubljene = emptyList()
                            shraniPriljubljene(kon, emptyList())
                            Toast.makeText(kon, "Vse počisteno", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Počisti vse")
                    }
                }
            }
        }

        if (prikaziDialogNajcenejsa && najcenejsaDrzava != null) {
            AlertDialog(
                onDismissRequest = { prikaziDialogNajcenejsa = false },
                title = { Text("Najcenejša država") },
                text = {
                    Column {
                        Text("${kodaVZastavo(najcenejsaDrzava!!.koda)} ${najcenejsaDrzava!!.ime}")
                        najcenejsaDrzava!!.cenaDizla?.let {
                            Text("Dizel: %.2f %s/%s".format(it, najcenejsaDrzava!!.valuta, najcenejsaDrzava!!.enota))
                        }
                        najcenejsaDrzava!!.cenaBencina?.let {
                            Text("Bencin: %.2f %s/%s".format(it, najcenejsaDrzava!!.valuta, najcenejsaDrzava!!.enota))
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { prikaziDialogNajcenejsa = false }) { Text("Zapri") }
                }
            )
        }
    }

    private suspend fun najcenejsaDrzavaDizel(): Bencinska? {
        val vse = dobiVse()
        return vse.filter { it.cenaDizla != null }
            .minByOrNull { it.cenaDizla!! }
    }
}

@Composable
fun Zadetek(podatki: Bencinska, obShrani: () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(12.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Stolpec(podatki)
            Button(onClick = obShrani) { Text("Shrani") }
        }
    }
}

@Composable
fun Priljubljena(podatki: Shranjena, obOdstrani: () -> Unit, obOsvezi: () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(12.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Stolpec(podatki, Modifier.weight(1f))
            Row {
                IconButton(onClick = obOsvezi) { Icon(Icons.Default.Refresh, null) }
                Button(onClick = obOdstrani, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text("Odstrani")
                }
            }
        }
    }
}

@Composable
fun Stolpec(podatki: Any, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        when (podatki) {
            is Bencinska -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(kodaVZastavo(podatki.koda), fontSize = MaterialTheme.typography.titleMedium.fontSize)
                    Spacer(Modifier.width(4.dp))
                    Text(podatki.ime, style = MaterialTheme.typography.titleMedium)
                }
                Text("Celotna država", style = MaterialTheme.typography.bodySmall)
                podatki.cenaDizla?.let {
                    Text("Dizel: %.2f %s/%s".format(it, podatki.valuta, podatki.enota))
                }
                podatki.cenaBencina?.let {
                    Text("Bencin: %.2f %s/%s".format(it, podatki.valuta, podatki.enota))
                }
            }
            is Shranjena -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(kodaVZastavo(podatki.koda), fontSize = MaterialTheme.typography.titleMedium.fontSize)
                    Spacer(Modifier.width(4.dp))
                    Text(podatki.ime, style = MaterialTheme.typography.titleMedium)
                }
                Text("Celotna država", style = MaterialTheme.typography.bodySmall)
                podatki.cenaDizla?.let {
                    Text("Dizel: %.2f %s/%s".format(it, podatki.valuta, podatki.enota))
                }
                podatki.cenaBencina?.let {
                    Text("Bencin: %.2f %s/%s".format(it, podatki.valuta, podatki.enota))
                }
            }
        }
    }
}