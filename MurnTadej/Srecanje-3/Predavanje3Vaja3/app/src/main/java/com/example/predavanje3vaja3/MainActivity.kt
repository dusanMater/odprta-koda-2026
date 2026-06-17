package com.example.predavanje3vaja3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje3vaja3.ui.theme.Predavanje3Vaja3Theme
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Urnik()
        }
    }
}

@Composable
fun Urnik() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF212B52))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(Modifier.height(12.dp))


        Text(
            text = "Moj urnik",
            fontSize = 32.sp,
            color = Color.White
        )


        Spacer(Modifier.height(20.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF354375))
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "8:00",
                color = Color(0xFFE5B24A),
                fontSize = 20.sp
            )
            Text(
                text = "Programiranje",
                color = Color.White,
                fontSize = 18.sp
            )
        }


        Spacer(Modifier.height(10.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF354375))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "9:00",
                color = Color(0xFFE5B24A),
                fontSize = 20.sp
            )
            Text(
                text = "Baze podatkov",
                color = Color.White,
                fontSize = 18.sp
            )
        }


        Spacer(Modifier.height(10.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF354375))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "10:00",
                color = Color(0xFFE5B24A),
                fontSize = 20.sp
            )
            Text(
                text = "Spletne strani",
                color = Color.White,
                fontSize = 18.sp
            )
        }


        Spacer(Modifier.height(10.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF354375))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "11:00",
                color = Color(0xFFE5B24A),
                fontSize = 20.sp
            )
            Text(
                text = "Omrežja",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}