package com.example.domacanaloga_06bussinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domacanaloga_06bussinesscard.ui.theme.DomacaNaloga06BussinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column (
                modifier = Modifier.fillMaxSize()
            ) {
                Introduction("Ammar Ćordić", modifier = Modifier.weight(3f).fillMaxWidth())
                Contact(modifier = Modifier.weight(1f).fillMaxWidth().padding(bottom = 48.dp).height(100.dp))
            }
        }
    }
}

@Composable
fun Introduction(name: String, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.android),
            contentDescription = "Android Logo",
            modifier = Modifier.padding(8.dp).size(180.dp, 140.dp),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = name,
            modifier = Modifier.padding(8.dp),
            fontSize = 32.sp
        )

        Text(
            text = "Software Developer",
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp,
            color = Color.Green
        )
    }
}

@Composable
fun Contact(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Phone Icon",
                modifier = Modifier.size(24.dp).weight(1f),
                alignment = Alignment.CenterEnd
            )
            Text(
                text = "+386 69 817 069",
                modifier = Modifier.padding(start = 24.dp).weight(2f),
                textAlign = TextAlign.Left
            )
        }

        Row (
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.share),
                contentDescription = "Share Icon",
                modifier = Modifier.size(24.dp).weight(1f),
                alignment = Alignment.CenterEnd
            )
            Text(
                text = "@ammarcordic",
                modifier = Modifier.padding(start = 24.dp).weight(2f),
                textAlign = TextAlign.Left
            )
        }

        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "Email Icon",
                modifier = Modifier.size(24.dp).weight(1f),
                alignment = Alignment.CenterEnd
            )
            Text(
                text = "ammar.cordic@almamater.si",
                modifier = Modifier.padding(start = 24.dp).weight(2f),
                textAlign = TextAlign.Left
            )
        }
    }
}