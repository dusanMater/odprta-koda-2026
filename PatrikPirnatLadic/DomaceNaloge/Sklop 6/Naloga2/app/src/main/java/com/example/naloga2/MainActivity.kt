package com.example.naloga2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Display(
                Main = "Jetpack Compose tutorial",
                Sub = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae libero sed neque luctus posuere. Donec facilisis, sapien at dictum gravida, mi lectus vulputate massa, at pulvinar nisl turpis vitae velit. Praesent euismod magna non sem consequat, sed consequat risus tincidunt. Curabitur feugiat, metus vel posuere varius, nibh neque blandit lacus, vitae dignissim lorem urna non augue."
            )
        }
    }
}

@Composable
fun Display(Main: String, Sub: String) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(top = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .paint(
                    painterResource(R.drawable.glava),
                )
        ) {}
        Main(Main = Main, modifier = Modifier.padding(16.dp))
        Sub(Sub = Sub, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun Main(Main: String, modifier: Modifier = Modifier) {
    Text(
        text = Main,
        fontSize = 24.sp,
        modifier = modifier  // actually use it
    )
}

@Composable
fun Sub(Sub: String, modifier: Modifier = Modifier) {
    Text(
        text = Sub,
        fontSize = 20.sp,
        textAlign = TextAlign.Justify,
        modifier = modifier  // actually use it
    )
}
