package com.example.trackyfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
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

@Composable
fun WeightScreen(navController: NavController, viewModel: WeightViewModel) {

    var weightInput by remember { mutableStateOf("") }
    val trend = viewModel.getTrend()

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
                    text = "Teža",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Sledenje napredku",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text("← Nazaj", color = TextSecondary, fontSize = 14.sp)
            }
        }

        // Trend card
        if (trend.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
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
                    Text(
                        text = "Sprememba",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = trend,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (trend.contains("↓")) Color(0xFF2E7D32) else if (trend.contains("↑")) Color(0xFFE53935) else TextSecondary
                    )
                }
            }
        }

        // Vnos
        Text(
            text = "Današnja teža",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            placeholder = { Text("npr. 75.5", color = TextTertiary) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = DividerColor,
                focusedBorderColor = Black
            ),
            singleLine = true,
            suffix = { Text("kg", color = TextSecondary) }
        )

        Button(
            onClick = {
                val w = weightInput.toDoubleOrNull()
                if (w != null) {
                    viewModel.addEntry(w)
                    weightInput = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black)
        ) {
            Text("Shrani", fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 24.dp),
            color = DividerColor
        )

        Text(
            text = "Zgodovina",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(viewModel.entries.reversed(), key = { it.id }) { entry ->
                SwipeToDeleteWeight(
                    entry = entry,
                    onDelete = { viewModel.deleteEntry(entry.id) }
                )
            }
        }
    }
}

@Composable
fun WeightCard(entry: WeightEntry) {
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
            Text(
                text = entry.date,
                fontSize = 14.sp,
                color = TextSecondary
            )
            Text(
                text = "${entry.weight} kg",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDeleteWeight(entry: WeightEntry, onDelete: () -> Unit) {
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
                        color = Color(0xFFE53935),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(end = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Zbriši",
                    tint = Color.White
                )
            }
        },
        dismissContent = {
            WeightCard(entry = entry)
        }
    )
}