package com.example.predavanje6vaja6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vizitka()
        }
    }
}

@Composable
fun Vizitka() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD2E8D4))
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logotip",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Tadej Murn",
                fontSize = 40.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Avtor tega programa",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF006D3B),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)   // cel blok na sredino
                .padding(bottom = 48.dp)
        ) {
            KontaktnaVrstica(Icons.Default.Phone, "+11 (123) 444 555 666")
            KontaktnaVrstica(Icons.Default.Share, "@AndroidDev")
            KontaktnaVrstica(Icons.Default.Email, "jen.doe@android.com")
        }
    }
}

@Composable
fun KontaktnaVrstica(ikona: androidx.compose.ui.graphics.vector.ImageVector, besedilo: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Icon(ikona, contentDescription = null, tint = Color(0xFF006D3B))
        Spacer(modifier = Modifier.width(16.dp))
        Text(besedilo)
    }
}