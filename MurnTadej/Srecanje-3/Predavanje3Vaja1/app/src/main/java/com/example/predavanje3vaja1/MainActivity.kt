package com.example.predavanje3vaja1

import android.R
import android.R.attr.fontWeight
import android.R.attr.name
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje3vaja1.ui.theme.Predavanje3Vaja1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vizitka()
                }
            }
        }


@Composable
fun Vizitka() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF99BBCC))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Tadej Murn",
            fontSize = 30.sp,
            color = Color(0xFF2196F3)
        )


        Spacer(Modifier.height(16.dp))


        Text(
            text = "Študent programa: ",
            fontSize = 15.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Spletne in informacijske tehnologije",
            fontSize = 15.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold
        )


        Spacer(Modifier.height(16.dp))


        Text(
            text = "Alma Mater Europaea",
            fontSize = 18.sp,
            color = Color(0xFF673AB7),
            fontWeight = FontWeight.Bold
        )


        Spacer(Modifier.height(24.dp))


        Text(
            "tadej.murn@almamater.si"
        )
    }
}
