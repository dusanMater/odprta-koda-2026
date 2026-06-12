package com.example.predavanje6vaja1

import android.R.attr.contentDescription
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje6vaja1.ui.theme.Predavanje6vaja1Theme

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
fun Vizitka(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
    ){

        //ozadje
        Image(
            painter = painterResource(R.drawable.ozadje),
            contentDescription = "Ozadje",
            contentScale = ContentScale.Crop, // da je cez cel zaslon
            modifier = Modifier.fillMaxSize()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Vsenajnaj("Dušan")


            Spacer(modifier = Modifier.height(20.dp))


            Posiljatelj("Tadej")

        }
    }
}
@Composable
fun Vsenajnaj(imeS: String){
    Text("Vse najboljše dragi $imeS", fontSize = 76.sp, textAlign = TextAlign.Center)
}
@Composable
fun Posiljatelj (imeP: String){
    Text("Ti želi $imeP", fontSize = 36.sp, textAlign = TextAlign.Center)
}
