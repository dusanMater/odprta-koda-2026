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
                Umetnina()
            }
        }
    }
}

data class PodatkiSlike(
    val slikaResId: Int,
    val naslov: String,
    val umetnik: String,
    val letnica: String
)

@Composable
fun Umetnina() {
    var indeks by remember { mutableIntStateOf(1) }

    val podatki = when (indeks) {
        1 -> PodatkiSlike(R.drawable.slika1, "The Kearsarge at Boulogne", "Edouard", "1864")
        2 -> PodatkiSlike(R.drawable.slika2, "Portrait of a Young Man", "Edouard", "1856")
        else -> PodatkiSlike(R.drawable.slika3, "Two Apples", "Edouard Manet", "1880")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        PrikazSlike(
            slikaId = podatki.slikaResId,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        Naslov(
            naslov = podatki.naslov,
            umetnik = podatki.umetnik,
            letnica = podatki.letnica,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    indeks = if (indeks == 1) 3 else indeks - 1
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A6572))
            ) {
                Text(text = "Prejsnja", fontSize = 16.sp, color = Color.White)
            }

            Button(
                onClick = {
                    indeks = if (indeks == 3) 1 else indeks + 1
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A6572))
            ) {
                Text(text = "Naslednja", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun PrikazSlike(
    slikaId: Int,
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
                painter = painterResource(id = slikaId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun Naslov(
    naslov: String,
    umetnik: String,
    letnica: String,
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
                    text = umetnik,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = " ($letnica)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
        }
    }
}