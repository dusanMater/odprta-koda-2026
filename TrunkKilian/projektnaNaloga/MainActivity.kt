package com.example.projektnanaloga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.projektnanaloga.model.Movie
import com.example.projektnanaloga.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@Composable
fun MovieApp() {
    var filmi by remember { mutableStateOf(listOf<Movie>()) }
    var iskanje by remember { mutableStateOf("") }
    var seNalaga by remember { mutableStateOf(false) }
    var napaka by remember { mutableStateOf("") }
    var konecIskanja by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val niFilmov = filmi.isEmpty()
    val apiKljuc = ""
    val modra = Color(0xFF00386C)
    val svetla = Color(0xFFDBEAF9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(svetla)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Iskalnik filmov",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = modra,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = iskanje,
            onValueChange = { iskanje = it },
            label = { Text("Vpiši film") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = modra,
                focusedLabelColor = modra,
                cursorColor = modra
            ),
            singleLine = true
        )

        Button(
            onClick = {
                seNalaga = true
                napaka = ""
                scope.launch {
                    try {
                        val response = RetrofitInstance.api.getFilmi(apiKljuc, iskanje, "sl-SI")
                        filmi = response.results
                        konecIskanja = true
                    } catch (e: Exception) {
                        napaka = "Napaka pri nalaganju filmov: " + e
                    }

                    seNalaga = false
                }
            },
            enabled = !seNalaga && iskanje != "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = modra,
                contentColor = Color.White
            )
        ) {
            Text(
                text = if (seNalaga) {
                    "Nalagam..."
                } else if (niFilmov) {
                    "Poišči filme"
                } else {
                    "Poišči znova"
                }
            )
        }

        if (seNalaga) {
            Text(
                text = "Filmi se nalagajo...",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else if (napaka != "") {
            Text(
                text = napaka,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else if (niFilmov && konecIskanja) {
            Text(
                text = "Ni rezultatov za ta naslov.",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else if (niFilmov) {
            Text(
                text = "Vpiši naslov filma in klikni gumb.",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else {
            for (film in filmi) {
                FilmKartica(film)
            }
        }
    }
}

@Composable
fun FilmKartica(film: Movie) {
    val modra = Color(0xFF00386C)
    val temnoSiva = Color(0xFF313638)
    val rdeca = Color(0xFFF95959)
    val slika = if (film.poster_path != null) {
        "https://image.tmdb.org/t/p/w500" + film.poster_path
    } else {
        "https://placehold.co/300x450/222222/FFFFFF.png?text=Ni+slike"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(slika),
            contentDescription = film.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 90.dp, height = 135.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(end = 12.dp)
        )

        Column {
            Text(
                text = film.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = modra,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Datum: " + film.release_date,
                fontSize = 16.sp,
                color = temnoSiva,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Ocena: " + film.vote_average,
                fontSize = 16.sp,
                color = rdeca,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = film.overview,
                fontSize = 16.sp,
                color = temnoSiva,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
    }
}
