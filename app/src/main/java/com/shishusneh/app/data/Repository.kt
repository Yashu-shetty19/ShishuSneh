package com.shishusneh.app.data

import android.content.Context
import com.shishusneh.app.db.AppDatabase
import com.shishusneh.app.db.entity.*

class Repository(context: Context) {
    private val db = AppDatabase.get(context)
    val profileDao = db.babyProfileDao()
    val growthDao = db.growthDao()
    val vaccineDao = db.vaccineDao()
    val milestoneDao = db.milestoneDao()

    suspend fun saveProfile(p: BabyProfile) = profileDao.upsert(p)
    suspend fun getProfile(): BabyProfile? = profileDao.get()

    suspend fun addGrowth(entry: GrowthEntry) = growthDao.insert(entry)
    suspend fun deleteGrowth(entry: GrowthEntry) = growthDao.delete(entry)

    suspend fun setVaccineCompleted(id: String, completed: Boolean) =
        vaccineDao.upsert(VaccineRecord(id, completed, if (completed) System.currentTimeMillis() else null))

    suspend fun setMilestone(id: String, reached: Boolean) =
        milestoneDao.upsert(MilestoneRecord(id, reached))
}
