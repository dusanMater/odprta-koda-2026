package com.example.domaca3

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OsebnaVizitka(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun OsebnaVizitka(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFCFE2F3))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Marko Tojagić",
            fontSize = 28.sp,
            color = Color.Blue
        )

        Text(
            text = "Študent",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Alma Mater Maribor",
            color = Color.Magenta

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "marko.tojagic@almamater.si"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DNPreview() {
    Domaca3Theme {
        OsebnaVizitka()
    }
}