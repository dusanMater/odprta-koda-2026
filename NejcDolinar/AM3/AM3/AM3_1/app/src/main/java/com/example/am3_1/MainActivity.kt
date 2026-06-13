package com.example.am3_1

import android.R.attr.text
import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.am3_1.ui.theme.AM3_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Display()
        }
    }
}

@Composable
fun Display() {
    Row(
        horizontalArrangement = Arrangement.Center

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("Dušan Fugima")

        }
        Text("Učitelj računalništva", modifier = Modifier.size(2.dp))
    }
    Spacer(modifier = Modifier.height(5.dp))

    Row(
    ) {
        Text("SERŠ Maribor", color = Color.Green, fontSize = 5.sp, modifier = Modifier.padding(top = 5.dp))
    }
    Row() {
        Text("dusan.fugima@sers.si", fontSize = 1.sp, modifier = Modifier.padding(top = 10.dp))
    }
}