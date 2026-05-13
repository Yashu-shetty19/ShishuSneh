package com.shishusneh.app.data

import com.shishusneh.app.db.entity.VaccineRecord
import java.text.SimpleDateFormat
import java.util.*

data class VaccineEvent(
    val id: String,
    val name: String,
    val ageLabel: String,
    val prevents: String,
    val dueDateMillis: Long,
    val status: Status
) {
    enum class Status { COMPLETED, OVERDUE, DUE_SOON, UPCOMING }

    fun dueDateFormatted(): String =
        SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(Date(dueDateMillis))
}

object VaccineScheduleBuilder {
    fun build(dobMillis: Long, records: List<VaccineRecord>): List<VaccineEvent> {
        val now = System.currentTimeMillis()
        val map = records.associateBy { it.vaccineId }
        return VaccineCatalog.SCHEDULE.map { def ->
            val due = VaccineCatalog.dueDateMillis(dobMillis, def)
            val completed = map[def.id]?.completed == true
            val daysUntil = (due - now) / (24L * 60 * 60 * 1000)
            val status = when {
                completed -> VaccineEvent.Status.COMPLETED
                daysUntil < -3 -> VaccineEvent.Status.OVERDUE
                daysUntil <= 7 -> VaccineEvent.Status.DUE_SOON
                else -> VaccineEvent.Status.UPCOMING
            }
            VaccineEvent(def.id, def.name, def.ageLabel, def.prevents, due, status)
        }
    }
}
