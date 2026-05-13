package com.shishusneh.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shishusneh.app.db.dao.*
import com.shishusneh.app.db.entity.*

@Database(
    entities = [BabyProfile::class, GrowthEntry::class, VaccineRecord::class, MilestoneRecord::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun babyProfileDao(): BabyProfileDao
    abstract fun growthDao(): GrowthDao
    abstract fun vaccineDao(): VaccineDao
    abstract fun milestoneDao(): MilestoneDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "shishu_sneh.db"
            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}
