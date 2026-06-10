package com.example.seminarska

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seminarska.model.TipPOI
import com.example.seminarska.network.RetrofitInstance
import com.example.seminarska.ui.theme.ModraGlava
import com.example.seminarska.ui.theme.PostItRumena
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ZaslonOdkrivanje(naPriljubljene: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var kraj by remember { mutableStateOf("Maribor") }
    var izbraniTip by remember { mutableStateOf(TipPOI.KAVARNE) }
    val rezultati = remember { mutableStateListOf<String>() }
    var zadnjaOsvezitev by remember { mutableStateOf("-") }
    var tipRazsirjen by remember { mutableStateOf(false) }
    var prikaziInfo by remember { mutableStateOf(false) }

    suspend fun izvediIskanje() {
        val krajTrim = kraj.trim()
        if (krajTrim.isEmpty()) {
            Toast.makeText(context, "Vnesi kraj iskanja", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val odgovor = RetrofitInstance.api.isciPoi(
                userAgent = "SEMINARSKA-POI/1.0 (urban.ambrozic@almamater.si)",
                poizvedba = "${izbraniTip.poizvedba} near $krajTrim"
            )
            rezultati.clear()
            rezultati.addAll(odgovor.map { it.display_name })
            zadnjaOsvezitev = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            if (odgovor.isEmpty()) {
                Toast.makeText(context, "Ni zadetkov", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Napaka pri iskanju", Toast.LENGTH_SHORT).show()
        }
    }

    fun zazeniIskanje() {
        scope.launch { izvediIskanje() }
    }

    LaunchedEffect(Unit) {
        izvediIskanje()
        while (true) {
            delay(300_000)
            izvediIskanje()
        }
    }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        Row(
            modifier = Modifier.fillMaxWidth().background(ModraGlava).padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Odkrivanje POI",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                "★",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.clickable { naPriljubljene() }
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            OutlinedTextField(
                value = kraj,
                onValueChange = { kraj = it },
                label = { Text("Kraj", fontSize = 16.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { zazeniIskanje() })
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = { tipRazsirjen = true },
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Text("Tip: ${izbraniTip.oznaka}", fontSize = 17.sp)
                }
                DropdownMenu(
                    expanded = tipRazsirjen,
                    onDismissRequest = { tipRazsirjen = false }
                ) {
                    TipPOI.values().forEach { tip ->
                        DropdownMenuItem(
                            text = { Text(tip.oznaka, fontSize = 18.sp) },
                            onClick = {
                                izbraniTip = tip
                                tipRazsirjen = false
                                zazeniIskanje()
                            }
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                Button(
                    onClick = { zazeniIskanje() },
                    contentPadding = PaddingValues(vertical = 14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Osveži", fontSize = 17.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { prikaziInfo = true },
                    contentPadding = PaddingValues(vertical = 14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("O Aplikaciji", fontSize = 17.sp)
                }
            }

            Text(
                "Zadnja osvežitev: $zadnjaOsvezitev",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 14.dp)
            )
            Text(
                "Lokacija: ${kraj.trim()} | Samodejno osveževanje: 5 min",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).background(PostItRumena)
        ) {
            items(rezultati) { ime ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val dodano = Shramba.dodaj(context, ime)
                            Toast.makeText(
                                context,
                                if (dodano) "Dodano med priljubljene" else "Že shranjeno",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("📍", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
                    Text(ime, fontSize = 19.sp)
                }
                HorizontalDivider()
            }
        }

        Text(
            "Tapni na POI za shranjevanje v priljubljene",
            fontStyle = FontStyle.Italic,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }

    if (prikaziInfo) {
        OAplikacijiDialog(onZapri = { prikaziInfo = false })
    }
}
