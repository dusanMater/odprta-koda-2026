package com.example.domaca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IgraHitrejsi(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IgraHitrejsi(modifier: Modifier = Modifier) {
    var tocke by remember { mutableStateOf(0) }
    var zmagovalec by remember { mutableStateOf("") }
    var igraTece by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Red)
                .clickable {
                    if (igraTece) {
                        tocke++
                        if (tocke >= 20) {
                            zmagovalec = "Zmagal je RDEČI!"
                            igraTece = false
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (!igraTece && tocke >= 20) {
                Text(text = zmagovalec, fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = if (igraTece) tocke.toString() else zmagovalec,
                onValueChange = {},
                readOnly = true,
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Blue)
                .clickable {
                    if (igraTece) {
                        tocke--
                        if (tocke <= -20) {
                            zmagovalec = "Zmagal je MODRI!"
                            igraTece = false
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (!igraTece && tocke <= -20) {
                Text(text = zmagovalec, fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}