package com.shishusneh.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shishusneh.app.db.entity.VaccineRecord

@Dao
interface VaccineDao {
    @Query("SELECT * FROM vaccine_record")
    fun observeAll(): LiveData<List<VaccineRecord>>

    @Query("SELECT * FROM vaccine_record")
    suspend fun getAll(): List<VaccineRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(record: VaccineRecord)
}
