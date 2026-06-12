package com.example.predavanje3vaja2

import android.R
import android.R.attr.name
import android.os.Bundle
import android.widget.Space
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje3vaja2.ui.theme.Predavanje3Vaja2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Meni()
            }
        }
    }

@Composable
fun Meni() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCBBFBA))
            .padding(30.dp)
    ) {


        Spacer(Modifier.height(12.dp))


        Text(
            text = "Jedilni List",
            fontSize = 34.sp,
            color = Color(0xFFF87850)
        )


        Spacer(Modifier.height(8.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pizza margerita")

            Spacer(Modifier.width(50.dp))

            Text(
                text = "8,50€",
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(Modifier.height(4.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Testanine")

            Spacer(Modifier.width(50.dp))

            Text(
                text = "7,20€",
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(Modifier.height(4.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Solata")

            Spacer(Modifier.width(50.dp))

            Text(
                text = "5,90€",
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
