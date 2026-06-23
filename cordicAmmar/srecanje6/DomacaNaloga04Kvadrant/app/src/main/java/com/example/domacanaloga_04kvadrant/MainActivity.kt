package com.example.domacanaloga_04kvadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domacanaloga_04kvadrant.ui.theme.DomacaNaloga04KvadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier.fillMaxSize().statusBarsPadding()
            ) {

                Row(
                    modifier = Modifier.weight(1f)
                ) {

                    Kvadrant(
                        title = "Text composable",
                        description = "Displays text and follows the recommended Material Design guidelines.",
                        color = Color(0xFFEADDFF),
                        modifier = Modifier.weight(1f)
                    )

                    Kvadrant(
                        title = "Image composable",
                        description = "Creates a composable that lays out and draws a given Painter class object.",
                        color = Color(0xFFD0BCFF),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.weight(1f)
                ) {

                    Kvadrant(
                        title = "Row composable",
                        description = "A layout composable that places its children in a horizontal sequence.",
                        color = Color(0xFFB69DF8),
                        modifier = Modifier.weight(1f)
                    )

                    Kvadrant(
                        title = "Column composable",
                        description = "A layout composable that places its children in a vertical sequence.",
                        color = Color(0xFFF6EDFF),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun Kvadrant(
    title: String,
    description: String,
    color: Color,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}