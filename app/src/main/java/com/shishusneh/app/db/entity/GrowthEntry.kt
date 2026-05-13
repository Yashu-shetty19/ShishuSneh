package com.shishusneh.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "growth_entry")
data class GrowthEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateMillis: Long,
    val weightKg: Float,
    val heightCm: Float
)
