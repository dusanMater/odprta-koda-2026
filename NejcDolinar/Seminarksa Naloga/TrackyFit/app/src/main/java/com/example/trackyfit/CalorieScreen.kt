package com.example.trackyfit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trackyfit.ui.theme.*

@Composable
fun CalorieScreen(navController: NavController) {

    val context = androidx.compose.ui.platform.LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    var weight by remember { mutableStateOf(userPrefs.getWeight()) }
    var height by remember { mutableStateOf(userPrefs.getHeight()) }
    var age by remember { mutableStateOf(userPrefs.getAge()) }
    var isMale by remember { mutableStateOf(userPrefs.getIsMale()) }
    var activityLevel by remember { mutableStateOf(1.2f) }
    var bmr by remember { mutableStateOf(0) }
    var tdee by remember { mutableStateOf(0) }
    var calculated by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Kalorije",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Kalkulator TDEE",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text("← Nazaj", color = TextSecondary, fontSize = 14.sp)
            }
        }

        // Spol
        Text(
            text = "Spol",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            GenderButton(label = "Moški", selected = isMale) { isMale = true }
            GenderButton(label = "Ženski", selected = !isMale) { isMale = false }
        }

        // Podatki
        Text(
            text = "Podatki",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            placeholder = { Text("Teža (kg)", color = TextTertiary) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = DividerColor,
                focusedBorderColor = Black
            ),
            singleLine = true
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            placeholder = { Text("Višina (cm)", color = TextTertiary) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = DividerColor,
                focusedBorderColor = Black
            ),
            singleLine = true
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            placeholder = { Text("Starost", color = TextTertiary) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = DividerColor,
                focusedBorderColor = Black
            ),
            singleLine = true
        )

        // Aktivnost
        Text(
            text = "Nivo aktivnosti",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            ActivityRow("Sedeč", "malo ali nič gibanja", 1.2f, activityLevel) { activityLevel = it }
            ActivityRow("Malo aktiven", "1–3x na teden", 1.375f, activityLevel) { activityLevel = it }
            ActivityRow("Zmerno aktiven", "3–5x na teden", 1.55f, activityLevel) { activityLevel = it }
            ActivityRow("Zelo aktiven", "6–7x na teden", 1.725f, activityLevel) { activityLevel = it }
            ActivityRow("Ekstremno aktiven", "2x na dan", 1.9f, activityLevel) { activityLevel = it }
        }

        // Gumb
        Button(
            onClick = {
                val w = weight.toDoubleOrNull()
                val h = height.toDoubleOrNull()
                val a = age.toIntOrNull()
                if (w != null && h != null && a != null) {
                    // Shrani podatke
                    userPrefs.saveUserData(weight, height, age, isMale)

                    val bmrVal = if (isMale) {
                        88.362 + (13.397 * w) + (4.799 * h) - (5.677 * a)
                    } else {
                        447.593 + (9.247 * w) + (3.098 * h) - (4.330 * a)
                    }
                    bmr = bmrVal.toInt()
                    tdee = (bmrVal * activityLevel).toInt()
                    calculated = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black)
        ) {
            Text("Izračunaj", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }

        // Rezultat
        if (calculated) {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Rezultat",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalorieResult(label = "BMR", value = bmr, subtitle = "v mirovanju")
                        VerticalDivider(modifier = Modifier.height(60.dp), color = DividerColor)
                        CalorieResult(label = "TDEE", value = tdee, subtitle = "z aktivnostjo")
                    }
                }
            }
        }
    }
}

@Composable
fun GenderButton(label: String, selected: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selected) Black else CardBackground,
            contentColor = if (selected) White else TextSecondary
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = if (selected) 0.dp else 1.dp
        )
    ) {
        Text(label, fontSize = 14.sp)
    }
}

@Composable
fun ActivityRow(title: String, subtitle: String, value: Float, selected: Float, onSelect: (Float) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = selected == value,
            onClick = { onSelect(value) },
            colors = RadioButtonDefaults.colors(selectedColor = Black)
        )
        Column {
            Text(text = title, fontSize = 14.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
            Text(text = subtitle, fontSize = 12.sp, color = TextTertiary)
        }
    }
}

@Composable
fun CalorieResult(label: String, value: Int, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 12.sp, color = TextSecondary)
        Text(
            text = "$value",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Text(text = "kcal", fontSize = 12.sp, color = TextSecondary)
        Text(text = subtitle, fontSize = 11.sp, color = TextTertiary)
    }
}