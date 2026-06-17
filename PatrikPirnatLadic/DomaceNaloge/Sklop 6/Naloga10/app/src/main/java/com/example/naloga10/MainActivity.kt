package com.example.naloga10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.naloga10.ui.theme.Naloga10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Naloga10Theme {
                Display()
            }
        }
    }
}

data class Artwork(val image: Int, val title: String, val artist: String, val year: Int)

@Composable
fun Display() {
    val artworks = listOf(
        Artwork(R.drawable.art1, "Sailing Under the Bridge", "Kat Kuan", 2017),
        Artwork(R.drawable.art2, "Some Other Art", "Artist Name", 2020),
        Artwork(R.drawable.art3, "Third Piece", "Another Artist", 2015)
    )

    var index by remember { mutableIntStateOf(0) }
    val current = artworks[index]

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(current.image),
            contentDescription = current.title,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(current.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("${current.artist} (${current.year})")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { if (index > 0) index-- }) { Text("Previous") }
            Button(onClick = { if (index < artworks.size - 1) index++ }) { Text("Next") }
        }
    }
}
