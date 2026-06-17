package com.example.predavanje4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


data class Oseba(
    val ime: String,
    val starost: Int,
    val virSlike: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PrikazFotografij()
        }
    }
}

@Composable
fun PrikazFotografij() {
       val osebe: List<Oseba> = listOf(
        Oseba(
            ime = "Ibrahim",
            starost = 24,
            virSlike = R.drawable.ibrahim
        ),
        Oseba(
            ime = "Ibrahim",
            starost = 24,
            virSlike = R.drawable.ibrahim
        ),
        Oseba(
            ime = "Ibrahim",
            starost = 24,
            virSlike = R.drawable.ibrahim
        ),
        Oseba(
            ime = "Ibrahim",
            starost = 24,
            virSlike = R.drawable.ibrahim

    )
    )

    Column {
        for(oseba :Oseba in osebe) {
            Vrstica(oseba)
        }
    }
}


@Composable
fun Vrstica(oseba: Oseba) {

    Spacer(modifier = Modifier.height(70.dp))


    Row {
        Image(
            painter = painterResource(id = oseba.virSlike),
            contentDescription = oseba.ime,
            modifier = Modifier
                .size(169.dp)
                .padding(bottom = 12.dp)
        )

        Column {
            Text("Ime: ${oseba.ime}")
            Text("Starost: ${oseba.starost}")
        }
    }
}