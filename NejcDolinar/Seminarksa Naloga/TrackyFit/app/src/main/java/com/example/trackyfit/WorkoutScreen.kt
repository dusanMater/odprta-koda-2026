package com.example.trackyfit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trackyfit.ui.theme.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.background

@Composable
fun WorkoutScreen(navController: NavController, viewModel: WorkoutViewModel) {

    var name by remember { mutableStateOf("") }
    var sets by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
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
                    text = "TrackyFit",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Moji treningi",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }
            Row {
                TextButton(onClick = { navController.navigate("weight") }) {
                    Text("Teža →", color = TextSecondary, fontSize = 14.sp)
                }
                TextButton(onClick = { navController.navigate("calories") }) {
                    Text("Kalorije →", color = TextSecondary, fontSize = 14.sp)
                }
            }
        }

        // Input sekcija
        Text(
            text = "Nov trening",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Ime treninga", color = TextTertiary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = DividerColor,
                focusedBorderColor = Black
            ),
            singleLine = true
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            OutlinedTextField(
                value = sets,
                onValueChange = { sets = it },
                placeholder = { Text("Seti", color = TextTertiary) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DividerColor,
                    focusedBorderColor = Black
                ),
                singleLine = true
            )
            OutlinedTextField(
                value = reps,
                onValueChange = { reps = it },
                placeholder = { Text("Reps", color = TextTertiary) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DividerColor,
                    focusedBorderColor = Black
                ),
                singleLine = true
            )
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                placeholder = { Text("kg", color = TextTertiary) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DividerColor,
                    focusedBorderColor = Black
                ),
                singleLine = true
            )
        }

        Button(
            onClick = {
                if (name.isNotBlank() && sets.isNotBlank() && reps.isNotBlank() && weight.isNotBlank()) {
                    viewModel.addWorkout(
                        name = name,
                        sets = sets.toIntOrNull() ?: 0,
                        reps = reps.toIntOrNull() ?: 0,
                        weight = weight.toDoubleOrNull() ?: 0.0
                    )
                    name = ""; sets = ""; reps = ""; weight = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black)
        ) {
            Text("Dodaj trening", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }

        // Divider
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 24.dp),
            color = DividerColor
        )

        // Lista
        Text(
            text = "Zgodovina",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(viewModel.workouts, key = { it.id }) { workout ->
                SwipeToDeleteWorkout(
                    workout = workout,
                    onDelete = { viewModel.deleteWorkout(workout.id) }
                )
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: Workout, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = workout.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${workout.sets} × ${workout.reps} reps · ${workout.weight} kg",
                    fontSize = 13.sp,
                    color = TextSecondary
                )
                Text(
                    text = workout.date,
                    fontSize = 12.sp,
                    color = TextTertiary
                )
            }
            IconButton(onClick = onDelete) {
                Text("×", fontSize = 20.sp, color = TextTertiary)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDeleteWorkout(workout: Workout, onDelete: () -> Unit) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                onDelete()
                true
            } else false
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = androidx.compose.ui.graphics.Color(0xFFE53935),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(end = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Zbriši",
                    tint = androidx.compose.ui.graphics.Color.White
                )
            }
        },
        dismissContent = {
            WorkoutCard(
                workout = workout,
                onDelete = onDelete
            )
        }
    )
}