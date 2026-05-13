package com.shishusneh.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.shishusneh.app.notification.NotificationHelper

class ShishuSnehApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(
                NotificationHelper.CHANNEL_ID,
                "Vaccination Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Reminders for baby's vaccinations" }
            nm.createNotificationChannel(channel)
        }
    }
}
