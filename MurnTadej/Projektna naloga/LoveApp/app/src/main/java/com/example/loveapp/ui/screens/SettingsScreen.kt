package com.example.loveapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.loveapp.notifications.AnniversaryWorker
import com.example.loveapp.ui.viewmodel.RelationshipViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: RelationshipViewModel,
    onBackClick: () -> Unit,
    onResetClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Lokalni state
    var myName by remember(state.isLoaded) { mutableStateOf(state.myName) }
    var partnerName by remember(state.isLoaded) { mutableStateOf(state.partnerName) }
    var startDate by remember(state.isLoaded) {
        mutableStateOf<Long?>(state.startDate.takeIf { it > 0 })
    }
    var newPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) newPhotoUri = uri
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = startDate,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long) =
                    utcTimeMillis <= System.currentTimeMillis()
            }
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    startDate = datePickerState.selectedDateMillis
                    showDatePicker = false
                }) { Text("Potrdi") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Prekliči") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Ponastavi aplikacijo?") },
            text = { Text("Vsi podatki (imena, datum, slika) bodo izbrisani in aplikacija se bo vrnila na začetni zaslon.") },
            confirmButton = {
                TextButton(onClick = {
                    showResetDialog = false
                    onResetClick()
                }) {
                    Text("Ponastavi", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) { Text("Prekliči") }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nastavitve") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Nazaj"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sekcija: slika
            Text(
                text = "Slika",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        imagePicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                val displayModel: Any? = newPhotoUri
                    ?: state.photoPath.takeIf { it.isNotBlank() }?.let { File(it) }

                if (displayModel != null) {
                    AsyncImage(
                        model = displayModel,
                        contentDescription = "Slika",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(Icons.Default.Edit, contentDescription = null)
                }
            }

            Text(
                text = "Klikni za zamenjavo",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // Sekcija: imeni in datum
            OutlinedTextField(
                value = myName,
                onValueChange = { myName = it },
                label = { Text("Tvoje ime") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = partnerName,
                onValueChange = { partnerName = it },
                label = { Text("Partnerjevo ime") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.DateRange, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = startDate?.let {
                        SimpleDateFormat("d. M. yyyy", Locale.getDefault()).format(Date(it))
                    } ?: "Izberi datum"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // Sekcija: notifikacije
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Obvestila ob mejnikih",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Obvešča te ob 100, 200, 365 dnevih, itd.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = state.notificationsEnabled,
                    onCheckedChange = { viewModel.toggleNotifications(it) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { AnniversaryWorker.sendTestNotification(context) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pošlji test notifikacijo")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Gumb shrani
            Button(
                onClick = {
                    viewModel.updateMyName(myName.trim())
                    viewModel.updatePartnerName(partnerName.trim())
                    startDate?.let { viewModel.updateStartDate(it) }
                    newPhotoUri?.let { viewModel.updatePhoto(it) }
                    onBackClick()
                },
                enabled = myName.isNotBlank() && partnerName.isNotBlank() && startDate != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Shrani spremembe")
            }

            // Razvojne možnosti - reset
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Razvojne možnosti",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { showResetDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Ponastavi aplikacijo (testno)")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}