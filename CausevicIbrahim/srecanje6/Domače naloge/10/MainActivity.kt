package com.example.domacanaloga_6_10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Galerija()
            }
        }
    }
}

data class SlikaPodatki(
    val vir: Int,
    val naslov: String,
    val avtor: String,
    val leto: String
)

@Composable
fun Galerija() {
    var trenutna by remember { mutableIntStateOf(1) }

    val podatki = when (trenutna) {
        1 -> SlikaPodatki(R.drawable.slika1, "Ibrahim Čaušević", "Ibrahim Č.", "2024")
        2 -> SlikaPodatki(R.drawable.slika3, "Yugo Koral 45", "Ibrahim Č.", "2025")
        else -> SlikaPodatki(R.drawable.slika3, "Spet Yugo", "Ibrahim Č.", "2026")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        OkvirSlika(
            slika = podatki.vir,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        KarticaOpis(
            naslov = podatki.naslov,
            avtor = podatki.avtor,
            leto = podatki.leto,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        )

        GumbiVrstica(
            naPrejsnjo = {
                trenutna = if (trenutna == 1) 3 else trenutna - 1
            },
            naNaslednjo = {
                trenutna = if (trenutna == 3) 1 else trenutna + 1
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun OkvirSlika(
    slika: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.shadow(8.dp, shape = RoundedCornerShape(4.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = slika),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun KarticaOpis(
    naslov: String,
    avtor: String,
    leto: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1)),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = naslov,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(
                    text = avtor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = " ($leto)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun GumbiVrstica(
    naPrejsnjo: () -> Unit,
    naNaslednjo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = naPrejsnjo,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A6572))
        ) {
            Text(text = "Prejšnja", fontSize = 16.sp, color = Color.White)
        }

        Button(
            onClick = naNaslednjo,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A6572))
        ) {
            Text(text = "Naslednja", fontSize = 16.sp, color = Color.White)
        }
    }
}