package com.shishusneh.app.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.shishusneh.app.R
import com.shishusneh.app.ui.onboarding.OnboardingActivity

object NotificationHelper {
    const val CHANNEL_ID = "vaccine_reminders"

    fun showVaccineReminder(context: Context, vaccineId: String, vaccineName: String, prevents: String) {
        val intent = Intent(context, OnboardingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pi = PendingIntent.getActivity(
            context, vaccineId.hashCode(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notif = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("💉 $vaccineName due")
            .setContentText("Protects baby from $prevents")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Today is the day for $vaccineName. It prevents $prevents. Visit your nearest health centre."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pi)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(vaccineId.hashCode(), notif)
    }
}
