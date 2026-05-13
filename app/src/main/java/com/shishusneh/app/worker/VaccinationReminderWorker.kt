package com.shishusneh.app.worker

import android.content.Context
import androidx.work.*
import com.shishusneh.app.data.VaccineCatalog
import com.shishusneh.app.notification.NotificationHelper
import java.util.concurrent.TimeUnit

class VaccinationReminderWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val vaccineId = inputData.getString(KEY_VACCINE_ID) ?: return Result.failure()
        val vaccineName = inputData.getString(KEY_VACCINE_NAME) ?: vaccineId
        val prevents = inputData.getString(KEY_PREVENTS) ?: ""
        NotificationHelper.showVaccineReminder(applicationContext, vaccineId, vaccineName, prevents)
        return Result.success()
    }

    companion object {
        const val KEY_VACCINE_ID = "vaccine_id"
        const val KEY_VACCINE_NAME = "vaccine_name"
        const val KEY_PREVENTS = "prevents"
        const val UNIQUE_PREFIX = "vaccine_reminder_"

        /**
         * Schedules one-time reminders for every upcoming vaccine, fired
         * 1 day before the due date. Uses unique work names so re-scheduling
         * is idempotent.
         */
        fun scheduleAll(context: Context, dobMillis: Long) {
            val wm = WorkManager.getInstance(context)
            val now = System.currentTimeMillis()
            VaccineCatalog.SCHEDULE.forEach { def ->
                val due = VaccineCatalog.dueDateMillis(dobMillis, def)
                val fireAt = due - TimeUnit.DAYS.toMillis(1)
                val delay = fireAt - now
                if (delay <= 0) return@forEach // already past

                val data = workDataOf(
                    KEY_VACCINE_ID to def.id,
                    KEY_VACCINE_NAME to def.name,
                    KEY_PREVENTS to def.prevents
                )
                val req = OneTimeWorkRequestBuilder<VaccinationReminderWorker>()
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .setInputData(data)
                    .addTag("vaccine_reminders")
                    .build()

                wm.enqueueUniqueWork(
                    UNIQUE_PREFIX + def.id,
                    ExistingWorkPolicy.REPLACE,
                    req
                )
            }
        }

        fun cancelAll(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag("vaccine_reminders")
        }
    }
}
