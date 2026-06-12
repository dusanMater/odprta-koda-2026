package com.example.osebnavizitka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.osebnavizitka.ui.theme.OsebnaVizitkaTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFED4FF))
            .padding(top = 50.dp, bottom = 500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text(
            text = "Urška Gril",
            color = Color(0xFF751478),
            fontSize = 40.sp
        )

        Text(
            text = "študentka",
            color = Color.DarkGray,
            fontSize = 20.sp
        )

        Text(
            text = "Alma Mater Europaea",
            color = Color.Blue,
            fontSize = 25.sp,
            modifier = Modifier.padding(top=20.dp, bottom=20.dp)
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "urska.gril@almamater.com",
            modifier = modifier
        )
    }
}

