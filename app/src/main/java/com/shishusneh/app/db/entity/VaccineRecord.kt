package com.shishusneh.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccine_record")
data class VaccineRecord(
    @PrimaryKey val vaccineId: String,
    val completed: Boolean = false,
    val completedDateMillis: Long? = null
)
