package com.example.projektnanaloga

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projektnanaloga.ui.theme.ProjektnaNalogaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.tan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjektnaNalogaTheme {
                ProjektnaNalogaApp()
            }
        }
    }
}

enum class Screen(val title: String) {
    Login("Prijava"),
    Menu("Domov"),
    CurrentLocation("Trenutna lokacija"),
    Weather("Trenutno vreme"),
    SavedLocations("Shranjene lokacije")
}

data class LocationData(val name: String, val latitude: String, val longitude: String)

data class WeatherData(
    val temperature: String,
    val description: String,
    val cloudCover: String,
    val sunrise: String
)

data class MapTile(val dx: Int, val dy: Int, val bitmap: Bitmap)

@Composable
fun ProjektnaNalogaApp() {
    val activity = LocalContext.current as? Activity
    var screen by remember { mutableStateOf(Screen.Login) }
    var currentLocation by remember { mutableStateOf(LocationData("Trenutna lokacija", "46.4199", "15.8702")) }
    val savedLocations = remember {
        mutableStateListOf(
            LocationData("Ptuj", "46.4199", "15.8702"),
            LocationData("Maribor", "46.5547", "15.6459")
        )
    }

    AppFrame(title = screen.title) {
        when (screen) {
            Screen.Login -> LoginScreen(
                onLogin = { screen = Screen.Menu },
                onExit = { activity?.finish() }
            )
            Screen.Menu -> MenuScreen(
                onCurrentLocation = { screen = Screen.CurrentLocation },
                onSavedLocations = { screen = Screen.SavedLocations },
                onLogout = { screen = Screen.Login },
                onExit = { activity?.finish() }
            )
            Screen.CurrentLocation -> CurrentLocationScreen(
                location = currentLocation,
                onLocationChange = { currentLocation = it },
                onHome = { screen = Screen.Menu },
                onWeather = { screen = Screen.Weather },
                onManual = { screen = Screen.SavedLocations }
            )
            Screen.Weather -> WeatherScreen(
                location = currentLocation,
                onHome = { screen = Screen.Menu },
                onBack = { screen = Screen.CurrentLocation }
            )
            Screen.SavedLocations -> SavedLocationsScreen(
                savedLocations = savedLocations,
                onUseLocation = {
                    currentLocation = it
                    screen = Screen.Weather
                },
                onHome = { screen = Screen.Menu },
                onBack = { screen = Screen.Menu }
            )
        }
    }
}

@Composable
fun AppFrame(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF3F51B5))
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, color = Color.White, fontSize = 22.sp)
            Text("⋮", color = Color.White, fontSize = 28.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .border(2.dp, Color.Black)
                .background(Color(0xFFE9E9E9))
        ) {
            content()
        }
    }
}

@Composable
fun LoginScreen(onLogin: () -> Unit, onExit: () -> Unit) {
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                error = false
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = error,
            modifier = Modifier.fillMaxWidth()
        )
        if (error) Text("Napačno geslo", color = Color.Red, modifier = Modifier.padding(top = 6.dp))
        AppButton("Prijava", onClick = {
            if (password == "Test123") onLogin() else error = true
        }, modifier = Modifier.padding(top = 10.dp))
        AppButton("Izhod", color = Color.Red, onClick = onExit, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
fun MenuScreen(onCurrentLocation: () -> Unit, onSavedLocations: () -> Unit, onLogout: () -> Unit, onExit: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppButton("Trenutna lokacija", onClick = onCurrentLocation)
        AppButton("Shranjene lokacije", onClick = onSavedLocations, modifier = Modifier.padding(top = 10.dp))
        AppButton("Odjava", color = Color(0xFF9E9E9E), onClick = onLogout, modifier = Modifier.padding(top = 10.dp))
        AppButton("Izhod", color = Color.Red, onClick = onExit, modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
fun CurrentLocationScreen(
    location: LocationData,
    onLocationChange: (LocationData) -> Unit,
    onHome: () -> Unit,
    onWeather: () -> Unit,
    onManual: () -> Unit
) {
    var latitude by remember(location) { mutableStateOf(location.latitude) }
    var longitude by remember(location) { mutableStateOf(location.longitude) }

    Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        MapPreview(
            latitude = latitude,
            longitude = longitude,
            modifier = Modifier.weight(1f),
            onLocationChanged = { newLatitude, newLongitude ->
                latitude = "%.4f".format(newLatitude)
                longitude = "%.4f".format(newLongitude)
            }
        )
        HeaderRow("Širina", "Dolžina")
        Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SmallField(latitude, { latitude = it }, Modifier.weight(1f))
            SmallField(longitude, { longitude = it }, Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            BottomButton("Domov", onHome, Modifier.weight(1f))
            BottomButton("Vreme na\nlokaciji", {
                onLocationChange(LocationData("Izbrana lokacija", latitude, longitude))
                onWeather()
            }, Modifier.weight(1f))
            BottomButton("Ročni vnos\nlokacije", {
                onLocationChange(LocationData("Ročni vnos", latitude, longitude))
                onManual()
            }, Modifier.weight(1f))
        }
    }
}

@Composable
fun WeatherScreen(location: LocationData, onHome: () -> Unit, onBack: () -> Unit) {
    var weather by remember { mutableStateOf<WeatherData?>(null) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    fun refresh() {
        scope.launch {
            loading = true
            error = null
            weather = runCatching { loadWeather(location.latitude, location.longitude) }
                .onFailure { error = "Napaka pri nalaganju vremena" }
                .getOrNull()
            loading = false
        }
    }

    LaunchedEffect(location) { refresh() }

    Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Text("Vreme na lokaciji:", fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(8.dp))
        HeaderRow("Širina", "Dolžina")
        ReadOnlyRow(location.latitude, location.longitude)
        HeaderRow("Temperatura", "Vreme")
        ReadOnlyRow(weather?.temperature.orEmpty(), weather?.description.orEmpty())
        HeaderRow("Oblaki", "Vzhod")
        ReadOnlyRow(weather?.cloudCover.orEmpty(), weather?.sunrise.orEmpty())
        Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            BottomButton("Domov", onHome, Modifier.weight(1f))
            BottomButton("Nazaj", onBack, Modifier.weight(1f))
            BottomButton("Osveži", { refresh() }, Modifier.weight(1f))
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when {
                loading -> CircularProgressIndicator()
                error != null -> Text(error.orEmpty(), color = Color.Red)
            }
        }
    }
}

@Composable
fun SavedLocationsScreen(
    savedLocations: MutableList<LocationData>,
    onUseLocation: (LocationData) -> Unit,
    onHome: () -> Unit,
    onBack: () -> Unit
) {
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<LocationData?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {
        Text("Dodaj lokacijo", fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(8.dp))
        HeaderRow("Širina", "Dolžina")
        Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SmallField(latitude, { latitude = it }, Modifier.weight(1f))
            SmallField(longitude, { longitude = it }, Modifier.weight(1f))
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f).background(Color.Black).padding(4.dp)) {
            items(savedLocations) { loc ->
                Button(
                    onClick = { selected = loc },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selected == loc) Color(0xFF777777) else Color(0xFFDDDDDD)),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                ) {
                    Text("${loc.name}: ${loc.latitude}, ${loc.longitude}", color = Color.Black)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            BottomButton("Domov", onHome, Modifier.weight(1f))
            BottomButton("Nazaj", onBack, Modifier.weight(1f))
            BottomButton("Shrani", {
                if (latitude.isNotBlank() && longitude.isNotBlank()) {
                    savedLocations.add(LocationData("Shranjena lokacija", latitude, longitude))
                    latitude = ""
                    longitude = ""
                }
            }, Modifier.weight(1f))
            BottomButton("Izbriši", {
                selected?.let { savedLocations.remove(it) }
                selected = null
            }, Modifier.weight(1f))
        }
        selected?.let { BottomButton("Prikaži vreme za izbrano", { onUseLocation(it) }, Modifier.fillMaxWidth().padding(4.dp)) }
    }
}

@Composable
fun HeaderRow(left: String, right: String) {
    Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(vertical = 6.dp)) {
        Text(left, fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
        Text(right, fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
    }
}

@Composable
fun ReadOnlyRow(left: String, right: String) {
    Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(left, modifier = Modifier.weight(1f).height(38.dp).border(1.dp, Color.Gray).padding(8.dp), fontSize = 16.sp)
        Text(right, modifier = Modifier.weight(1f).height(38.dp).border(1.dp, Color.Gray).padding(8.dp), fontSize = 16.sp)
    }
}

@Composable
fun SmallField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier.height(54.dp)
    )
}

@Composable
fun AppButton(text: String, color: Color = Color(0xFFE0E0E0), onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(2.dp),
        modifier = modifier.fillMaxWidth().height(56.dp)
    ) {
        Text(text, color = Color.Black)
    }
}

@Composable
fun BottomButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier.height(54.dp)
    ) {
        Text(text, color = Color.Black, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun MapPreview(latitude: String, longitude: String, modifier: Modifier = Modifier, onLocationChanged: (Double, Double) -> Unit = { _, _ -> }) {
    val lat = latitude.toDoubleOrNull() ?: 46.4199
    val lon = longitude.toDoubleOrNull() ?: 15.8702
    var tiles by remember { mutableStateOf<List<MapTile>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var dragTotal by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(lat, lon) {
        loading = true
        error = null
        tiles = runCatching { loadMapTiles(lat, lon) }
            .onFailure { error = "Zemljevida ni bilo mogoče naložiti" }
            .getOrDefault(emptyList())
        loading = false
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds()
            .pointerInput(lat, lon) {
                detectDragGestures(
                    onDragStart = { dragTotal = Offset.Zero },
                    onDrag = { _, dragAmount -> dragTotal += dragAmount },
                    onDragEnd = {
                        val zoom = 13
                        val pixelsInWorld = (1 shl zoom) * 256.0
                        val lonPerPixel = 360.0 / pixelsInWorld
                        val latPerPixel = lonPerPixel * cos(lat * PI / 180.0)
                        val newLongitude = (lon - dragTotal.x * lonPerPixel).coerceIn(-180.0, 180.0)
                        val newLatitude = (lat + dragTotal.y * latPerPixel).coerceIn(-85.0, 85.0)
                        onLocationChanged(newLatitude, newLongitude)
                    }
                )
            }
            .border(2.dp, Color.Black)
            .background(Color(0xFFDDEAF3))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val tileSize = max(size.width, size.height) / 2.2f
            val startX = (size.width - tileSize * 3f) / 2f
            val startY = (size.height - tileSize * 3f) / 2f

            tiles.forEach { tile ->
                val left = startX + (tile.dx + 1) * tileSize
                val top = startY + (tile.dy + 1) * tileSize
                drawImage(
                    image = tile.bitmap.asImageBitmap(),
                    dstOffset = IntOffset(left.roundToInt(), top.roundToInt()),
                    dstSize = IntSize(tileSize.roundToInt(), tileSize.roundToInt())
                )
            }

            drawCircle(Color(0xFF03A9F4), radius = 18f, center = center)
            drawCircle(Color.White, radius = 10f, center = center)
        }

        Text("+\n−", modifier = Modifier.padding(12.dp).background(Color.White).padding(horizontal = 10.dp, vertical = 6.dp), fontSize = 26.sp, lineHeight = 28.sp)
        Text("10 km", modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp).background(Color.White.copy(alpha = 0.85f)).padding(horizontal = 6.dp), color = Color.DarkGray)
        Text("$latitude, $longitude", modifier = Modifier.align(Alignment.BottomStart).padding(10.dp).background(Color.White.copy(alpha = 0.85f)).padding(horizontal = 6.dp), color = Color.DarkGray, fontSize = 12.sp)

        when {
            loading -> Text("Nalagam zemljevid...", modifier = Modifier.align(Alignment.Center).background(Color.White).padding(8.dp))
            error != null -> Text(error.orEmpty(), color = Color.Red, modifier = Modifier.align(Alignment.Center).background(Color.White).padding(8.dp))
        }
    }
}

suspend fun loadMapTiles(latitude: Double, longitude: Double): List<MapTile> = withContext(Dispatchers.IO) {
    val zoom = 13
    val n = 1 shl zoom
    val latRad = latitude * PI / 180.0
    val centerX = floor((longitude + 180.0) / 360.0 * n).toInt()
    val centerY = floor((1.0 - ln(tan(latRad) + 1.0 / cos(latRad)) / PI) / 2.0 * n).toInt()
    val result = mutableListOf<MapTile>()

    for (dy in -1..1) {
        for (dx in -1..1) {
            val x = ((centerX + dx) % n + n) % n
            val y = centerY + dy
            val bitmap = URL("https://a.basemaps.cartocdn.com/rastertiles/voyager/$zoom/$x/$y.png")
                .openConnection()
                .apply { setRequestProperty("User-Agent", "ProjektnaNalogaWeatherApp/1.0") }
                .getInputStream()
                .use { BitmapFactory.decodeStream(it) }
            result.add(MapTile(dx, dy, bitmap))
        }
    }

    result
}


suspend fun loadWeather(latitude: String, longitude: String): WeatherData = withContext(Dispatchers.IO) {
    val lat = latitude.toDoubleOrNull() ?: 46.4199
    val lon = longitude.toDoubleOrNull() ?: 15.8702
    val url = "https://api.open-meteo.com/v1/forecast" +
        "?latitude=$lat&longitude=$lon" +
        "&current=temperature_2m,weather_code,cloud_cover" +
        "&daily=sunrise&timezone=auto"
    val json = JSONObject(URL(url).readText())
    val current = json.getJSONObject("current")
    val daily = json.getJSONObject("daily")
    val temperature = "${current.getDouble("temperature_2m").roundToInt()} °C"
    val description = weatherDescription(current.getInt("weather_code"))
    val clouds = "${current.getInt("cloud_cover")} %"
    val sunrise = daily.getJSONArray("sunrise").getString(0).substringAfter("T").take(5)
    WeatherData(temperature, description, clouds, sunrise)
}

fun weatherDescription(code: Int): String = when (code) {
    0 -> "Jasno"
    1, 2 -> "Delno jasno"
    3 -> "Oblačno"
    45, 48 -> "Megla"
    51, 53, 55, 61, 63, 65, 80, 81, 82 -> "Dež"
    71, 73, 75, 77, 85, 86 -> "Sneg"
    95, 96, 99 -> "Nevihta"
    else -> "Podatki"
}
