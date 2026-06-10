package com.example.vaja_03_kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaja_03_kalkulator.ui.theme.Vaja03kalkulatorTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vaja03kalkulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KalkulatorNapitnine(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// glavni zaslon
@Composable
fun KalkulatorNapitnine(modifier: Modifier = Modifier) {

    var vrednostNakupa by remember { mutableStateOf("") }
    var odstotekNapitnine by remember { mutableStateOf("") }
    var zaokrozi by remember { mutableStateOf(false) }


    val znesek = vrednostNakupa.toDoubleOrNull() ?: 0.0
    val odstotek = odstotekNapitnine.toDoubleOrNull() ?: 0.0
    val napitnina = izracunajNapitnino(znesek, odstotek, zaokrozi)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // naslov
        NaslovVrstica(modifier = Modifier.padding(top = 24.dp, bottom = 32.dp))

        // polje za vrednost nakupa
        VnosnoPolje(
            oznaka = "Cost of Service",
            vrednost = vrednostNakupa,
            onSpremembaVrednosti = { vrednostNakupa = it },
            tipkovnicaMoznosti = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // polje za procent napitnine
        VnosnoPolje(
            oznaka = "Tip (%)",
            vrednost = odstotekNapitnine,
            onSpremembaVrednosti = { odstotekNapitnine = it },
            tipkovnicaMoznosti = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // vrstica z gumbom
        ZaokroziVrstica(
            zaokrozi = zaokrozi,
            onSpremembaZaokrozi = { zaokrozi = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        // izpis napitnine
        ZnesekNapitnineVrstica(
            znesek = napitnina,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

// funkcija za naslov
@Composable
fun NaslovVrstica(modifier: Modifier = Modifier) {
    Text(
        text = "Calculate Tip",
        fontSize = 56.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

// eno vnosno polje, uporabim ga dvakrat z razlicnimi podatki
@Composable
fun VnosnoPolje(
    oznaka: String,
    vrednost: String,
    onSpremembaVrednosti: (String) -> Unit,
    tipkovnicaMoznosti: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = vrednost,
        onValueChange = onSpremembaVrednosti,
        placeholder = { Text(text = oznaka, fontSize = 30.sp) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 30.sp),
        keyboardOptions = tipkovnicaMoznosti,
        modifier = modifier
    )
}

// vrstica z napisom in gumbom
@Composable
fun ZaokroziVrstica(
    zaokrozi: Boolean,
    onSpremembaZaokrozi: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Round up tip?",
            fontSize = 32.sp
        )
        // malo razmika do stikala
        Spacer(modifier = Modifier.width(24.dp))
        // stikalo malo povecam da se ujema z besedilom v vrstici
        Switch(
            checked = zaokrozi,
            onCheckedChange = onSpremembaZaokrozi,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .scale(1.2f)
        )
    }
}

// vrstica ki pokaze znesek napitnine
@Composable
fun ZnesekNapitnineVrstica(znesek: String, modifier: Modifier = Modifier) {
    Text(
        text = "Tip Amount: $znesek",
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

// popravljena funkcija za izracun napitnine (dodal zaokrozevanje navzgor)
fun izracunajNapitnino(znesek: Double, odstotek: Double, zaokrozi: Boolean): String {
    var napitnina = odstotek / 100 * znesek
    if (zaokrozi) {
        napitnina = ceil(napitnina)
    }
    return NumberFormat.getCurrencyInstance().format(napitnina)
}

@Preview(showBackground = true)
@Composable
fun KalkulatorNapitninePreview() {
    Vaja03kalkulatorTheme {
        KalkulatorNapitnine()
    }
}
