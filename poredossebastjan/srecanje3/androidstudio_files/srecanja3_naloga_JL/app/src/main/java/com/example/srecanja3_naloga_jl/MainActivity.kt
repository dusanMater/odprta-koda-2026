package com.example.srecanja3_naloga_jl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.srecanja3_naloga_jl.ui.theme.Srecanja3_naloga_JLTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

                JedilniList()
        }
    }
}

@Composable
fun JedilniList()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E9D7))
            .padding(25.dp)
    ) {

        Text(
            text = "Jedilni list",
            fontSize = 30.sp,
            color = Color(0xFFFF5C00)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Pizza margarita",
                fontSize = 20.sp
            )

            Text(
                text = "8,50 €",
                fontSize = 20.sp,
                color = Color(0xFF2E7D32)
            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Testenine",
                fontSize = 20.sp
            )

            Text(
                text = "7,20 €",
                fontSize = 20.sp,
                color = Color(0xFF2E7D32)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Solata",
                fontSize = 20.sp
            )

            Text(
                text = "5,90 €",
                fontSize = 20.sp,
                color = Color(0xFF2E7D32)
            )
        }
    }
}
