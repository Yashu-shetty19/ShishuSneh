package com.shishusneh.app.worker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shishusneh.app.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** Re-schedules vaccine reminders after device reboot. */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return
        val pending = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val profile = AppDatabase.get(context).babyProfileDao().get()
                profile?.let { VaccinationReminderWorker.scheduleAll(context, it.dobMillis) }
            } finally {
                pending.finish()
            }
        }
    }
}
