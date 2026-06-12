package com.example.predavanje6vaja8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje6vaja8.ui.theme.Predavanje6vaja8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Limonada()
        }
    }
}

@Composable
fun Limonada() {
    var stopnja by remember { mutableStateOf(1) }
    var stklik by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFE085))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Lemonade", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (stopnja) {


                1 -> {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFFC3ECF6))
                            .clickable {
                                stopnja = 2
                                stklik = (2..8).random()
                            }
                            .padding(24.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.lemon_tree),
                            contentDescription = "Drevo"
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    Text("Tapni limonino drevo, da izbereš limono", fontSize = 18.sp)
                }


                2 -> {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFFC3ECF6))
                            .clickable {
                                stklik--
                                if (stklik == 0) stopnja = 3
                            }
                            .padding(24.dp)
                    ){
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = "Limona"
                    )}
                    Spacer(Modifier.height(20.dp))
                    Text("Tapkaj po limoni, da jo stisneš", fontSize = 18.sp)
                }


                3 -> {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFFC3ECF6))
                            .clickable {
                                stopnja = 4
                            }
                            .padding(24.dp)
                    ){
                        Image(
                            painter = painterResource(R.drawable.lemon_drink),
                            contentDescription = "Limonina pijača",
                        )}
                    Spacer(Modifier.height(20.dp))
                    Text("Klikni na limonado, da jo spiješ", fontSize = 18.sp)
                }


                4 -> {
                    Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFC3ECF6))
                        .clickable {
                            stopnja = 1
                        }
                        .padding(24.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = "kozarec",
                    )}
                    Spacer(Modifier.height(20.dp))
                    Text("Klikni prazen kozarec da resiteraš igro", fontSize = 18.sp)
                }
            }
        }
    }
}