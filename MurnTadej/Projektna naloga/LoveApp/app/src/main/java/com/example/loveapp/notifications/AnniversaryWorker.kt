package com.example.loveapp.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.loveapp.MainActivity
import com.example.loveapp.R
import com.example.loveapp.data.PreferencesManager
import kotlinx.coroutines.flow.first
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

class AnniversaryWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val prefs = PreferencesManager(applicationContext)

        // Preveri pogoje
        if (!prefs.notificationsEnabled.first()) return Result.success()
        if (!prefs.setupDone.first()) return Result.success()

        val startMillis = prefs.startDate.first()
        if (startMillis == 0L) return Result.success()

        val startDate = Instant.ofEpochMilli(startMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val today = LocalDate.now()

        val milestone = checkMilestone(startDate, today) ?: return Result.success()
        showNotification(applicationContext, milestone)

        return Result.success()
    }

    private fun checkMilestone(startDate: LocalDate, today: LocalDate): String? {
        val totalDays = ChronoUnit.DAYS.between(startDate, today)
        val period = Period.between(startDate, today)

        // 1. Letna obletnica (točno X let, brez mesecev/dni)
        if (period.years > 0 && period.months == 0 && period.days == 0) {
            return "💕 Vajina ${period.years}. obletnica je danes!"
        }

        // 2. Mesečnice v prvem letu (1, 3, 6, 9 mesecev)
        if (period.years == 0 && period.days == 0 && period.months in listOf(1, 3, 6, 9)) {
            val mesec = when (period.months) {
                1 -> "mesec"; 2 -> "meseca"; 3, 4 -> "meseci"; else -> "mesecev"
            }
            return "❤️ Skupaj že ${period.months} $mesec!"
        }

        // 3. Okrogli dnevi
        if (totalDays in listOf(100L, 200L, 500L, 1000L, 2000L, 5000L)) {
            return "🎉 Skupaj sta že $totalDays dni!"
        }

        return null
    }

    companion object {
        private const val CHANNEL_ID = "anniversary_channel"
        private const val NOTIFICATION_ID = 1001
        private const val WORK_NAME = "anniversary_check"

        // Zaženi periodični worker (enkrat dnevno)
        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<AnniversaryWorker>(
                1, TimeUnit.DAYS
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
        }

        // Za testiranje - pošlje notifikacijo takoj brez čakanja na worker
        fun sendTestNotification(context: Context) {
            showNotification(context, "🎉 Test: notifikacije delajo!")
        }

        // Skupna logika za prikaz notifikacije
        fun showNotification(context: Context, message: String) {
            // 1. Ustvari notifikacijski kanal (Android 8+ obvezno)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Mejniki razmerja",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Obvešča o obletnicah in mejnikih"
                }
                val manager = context.getSystemService(NotificationManager::class.java)
                manager?.createNotificationChannel(channel)
            }

            // 2. PendingIntent - klik na notifikacijo odpre aplikacijo
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            // 3. Sestavi notifikacijo
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Skupaj")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            // 4. Preveri permission (Android 13+) in pošlji
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context, Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return  // brez permission ne moremo
                }
            }
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
        }
    }
}