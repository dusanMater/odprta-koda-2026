package com.example.osebnavizitka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OsebnaVizitka()
        }
    }
}

// Barve vizitke, vgrajene kot variable, omejene na obseg te datoteke
private val barvaOzadja = Color(0xFFEEF2F7)
private val barvaKartice = Color.White
private val barvaTekstPrimarna = Color(0xFF0B2545)
private val barvaVloge = Color(0xFF5A6478)
private val barvaSole = Color(0xFF0F6E4D)
private val barvaObrobe = Color(0xFF0B2545)

@Composable
fun OsebnaVizitka() {
    Column(
        modifier = Modifier
            .fillMaxSize()    // zasede celotno velikost zaslona
            .background(barvaOzadja)
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Prazen prostor nad kartico - kartica je dvignjena višje od sredine
        Spacer(modifier = Modifier.height(250.dp))

        // Kartica vizitke z zaobljeno obrobo (dodatek)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(barvaKartice, RoundedCornerShape(24.dp))    // vrednost barve ozadja je klicana iz "zbirke" barv na vrhu
                .border(3.dp, barvaObrobe, RoundedCornerShape(24.dp))
                .padding(horizontal = 28.dp, vertical = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Urban Ambrožič",
                color = barvaTekstPrimarna,
                fontSize = 44.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "študent",
                color = barvaVloge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Alma Mater Europaea",
                color = barvaSole,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "urban.ambrozic@almamater.si",
                color = barvaTekstPrimarna,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
