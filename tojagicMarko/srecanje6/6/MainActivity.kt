package com.example.domacanaloga_6_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    .background(Color(0xFFD2E7D5))
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.alien),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFF073053))
                            .padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Jennifer Doe",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black
                    )

                    Text(
                        text = "Android Developer Extraordinaire",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Kontakt(emoji = "📞", podatek = "+11 (123) 444 555 666")
                    Kontakt(emoji = "📱", podatek = "@AndroidDev")
                    Kontakt(emoji = "✉️", podatek = "jen.doe@android.com")
                }
            }
        }
    }
}

@Composable
fun Kontakt(
    emoji: String,
    podatek: String
) {
    Row(
        modifier = Modifier
            .width(260.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = emoji,
            fontSize = 22.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = podatek,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}