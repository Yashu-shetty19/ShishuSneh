package com.shishusneh.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "baby_profile")
data class BabyProfile(
    @PrimaryKey val id: Int = 1, // single profile
    val name: String,
    val dobMillis: Long,
    val gender: String? = null
)
