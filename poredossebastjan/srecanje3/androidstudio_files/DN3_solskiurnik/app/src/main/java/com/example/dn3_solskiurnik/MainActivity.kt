package com.example.dn3_solskiurnik

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dn3_solskiurnik.ui.theme.DN3_solskiurnikTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            solskiurnik()

        }
    }
}

@Composable
fun solskiurnik()
{
Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF006B7D))
        .padding(all =30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top


) {

    Text(
        text = "Moj urnik",
        fontSize = 40.sp,
        color = Color.White
    )

    Spacer(modifier = Modifier.height(30.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5A757A))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
          text = "08:00",
          fontSize = 20.sp,
          color = Color(0xFFE0A84B)

      )

      Text(
          text="Programiranje",
          fontSize = 20.sp,
          color = Color.White
      )
    }

    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5A757A))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text="09:00",
            fontSize = 20.sp,
            color = Color(0xFFE0A84B)
        )

        Text(
            text="Matematika",
            fontSize = 20.sp,
            color = Color.White
        )
    }


    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5A757A))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text="10:00",
            fontSize = 20.sp,
            color = Color(0xFFE0A84B)
        )

        Text(
            text="Baza podatkov",
            fontSize = 20.sp,
            color = Color.White
        )
    }


    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5A757A))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text="11:00",
            fontSize = 20.sp,
            color = Color(0xFFE0A84B)
        )

        Text(
            text="Omrežja",
            fontSize = 20.sp,
            color = Color.White
        )
    }


}

}