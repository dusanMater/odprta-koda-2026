package com.example.predavanje6vaja3


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje6vaja3.ui.theme.Predavanje6vaja3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskComp()
        }
    }
}

@Composable
fun TaskComp() {
    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),               // da ima prostor za centriranje
            verticalArrangement = Arrangement.Center,        // navpično na sredino
            horizontalAlignment = Alignment.CenterHorizontally  // vodoravno na sredin
        ) {
            Image(
                painter = painterResource(R.drawable.ic_task_completed),
                contentDescription = "ikona",
                contentScale = ContentScale.Fit
            )

            Text(
                text = "All task completed",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = "Nice work!",
                fontSize = 16.sp
            )

        }
    }
}
