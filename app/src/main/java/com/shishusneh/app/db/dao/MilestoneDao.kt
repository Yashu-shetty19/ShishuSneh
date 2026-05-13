package com.shishusneh.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shishusneh.app.db.entity.MilestoneRecord

@Dao
interface MilestoneDao {
    @Query("SELECT * FROM milestone_record")
    fun observeAll(): LiveData<List<MilestoneRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(record: MilestoneRecord)
}
