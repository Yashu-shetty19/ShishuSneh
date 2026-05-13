package com.shishusneh.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "milestone_record")
data class MilestoneRecord(
    @PrimaryKey val milestoneId: String,
    val reached: Boolean = false
)
