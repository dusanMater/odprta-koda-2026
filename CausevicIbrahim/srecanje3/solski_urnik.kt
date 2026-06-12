package com.example.domacanaloga3_3
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MojUrnik_glavna()
                }
            }
        }
@Composable
fun MojUrnik_glavna() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF202A30))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Moj urnik",
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Urnik_vrstica(ura = "08:00", predmet = "Programiranje")
        Urnik_vrstica(ura = "09:00", predmet = "Baze podatkov")
        Urnik_vrstica(ura = "10:00", predmet = "Spletne strani")
        Urnik_vrstica(ura = "11:00", predmet = "Omrežja")
    }
}

@Composable
fun Urnik_vrstica(ura: String, predmet: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF2C3840))
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = ura,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFFFB300)
        )

        Text(
            text = predmet,
            fontSize = 18.sp,
            color = Color(0xFFE0E0E0)
        )
    }
}