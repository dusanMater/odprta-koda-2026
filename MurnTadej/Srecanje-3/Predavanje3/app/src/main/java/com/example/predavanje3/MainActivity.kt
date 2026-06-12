// 📦 PAKETI & IMPORTI — vedno na vrhu, avtomatsko se dodajo
package com.example.nakupovalniseznam
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.*
// ...

// 🗂️ DATA CLASS — pred MainActivity, "globalna" definicija
data class Artikel(
    val naziv: String,
    var vKosarici: Boolean = false
)

// 🏠 MAIN ACTIVITY — vstopna točka aplikacije, ena sama
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                NakupovalniSeznamScreen() // ← pokličemo našo UI funkcijo
            }
        }
    }

// 🎨 COMPOSABLE FUNKCIJE — pod MainActivity, toliko jih hočeš
@Composable
fun NakupovalniSeznamScreen() {
    Text("Živjo!")
}