package com.example.srecanje3_naloga


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
fun Vizitka()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDCE9F5)),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            modifier = Modifier
                .padding(top = 50.dp),
            text = "Sebastjan Poredoš",
            fontSize = 35.sp,
            color = Color.Blue,
        )

        Text(
            text = "študent AMEU",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ALMA MATER EUROPEA",
            fontSize = 20.sp,
            color = Color(0xFF2E7D32)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "sebastjan.poredos@almamater.si",
            fontSize = 20.sp
        )
    }
}