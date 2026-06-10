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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seminarska.ui.theme.ModraGlava
import com.example.seminarska.ui.theme.PostItRumena
import com.example.seminarska.ui.theme.RdecaGumb

@Composable
fun ZaslonPriljubljene(naNazaj: () -> Unit) {
    val context = LocalContext.current
    val priljubljene = remember { mutableStateListOf<String>() }
    var prikaziInfo by remember { mutableStateOf(false) }
    var prikaziPotrditev by remember { mutableStateOf(false) }

    fun osveziSeznam() {
        priljubljene.clear()
        priljubljene.addAll(Shramba.naloziPriljubljene(context))
    }

    LaunchedEffect(Unit) { osveziSeznam() }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        Row(
            modifier = Modifier.fillMaxWidth().background(ModraGlava).padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "←",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.clickable { naNazaj() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                "Priljubljene Točke",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            Text("Shranjenih: ${priljubljene.size} POI", fontSize = 18.sp)
            Row(modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                Button(
                    onClick = { prikaziPotrditev = true },
                    colors = ButtonDefaults.buttonColors(containerColor = RdecaGumb),
                    contentPadding = PaddingValues(vertical = 14.dp),
                    modifier = Modifier.weight(1f)
                ) { Text("Počisti vse", fontSize = 17.sp) }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { prikaziInfo = true },
                    contentPadding = PaddingValues(vertical = 14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("O Aplikaciji", fontSize = 17.sp)
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).background(PostItRumena)
        ) {
            items(priljubljene) { ime ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Shramba.odstrani(context, ime)
                            osveziSeznam()
                            Toast.makeText(context, "Odstranjeno iz priljubljenih", Toast.LENGTH_SHORT).show()
                        }
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⭐", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
                    Text(ime, fontSize = 19.sp)
                }
                HorizontalDivider()
            }
        }

        Text(
            "Shranjeno lokalno | Tapni POI, da ga odstraniš",
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }

    if (prikaziPotrditev) {
        AlertDialog(
            onDismissRequest = { prikaziPotrditev = false },
            title = { Text("Potrditev Brisanja") },
            text = { Text("Ali ste prepričani, da želite izbrisati vse priljubljene?") },
            confirmButton = {
                TextButton(onClick = {
                    Shramba.pocistiVse(context)
                    osveziSeznam()
                    prikaziPotrditev = false
                    Toast.makeText(context, "Vse priljubljene izbrisane", Toast.LENGTH_SHORT).show()
                }) { Text("Da, izbriši") }
            },
            dismissButton = {
                TextButton(onClick = { prikaziPotrditev = false }) { Text("Prekliči") }
            }
        )
    }

    if (prikaziInfo) {
        OAplikacijiDialog(onZapri = { prikaziInfo = false })
    }
}
