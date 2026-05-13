package com.shishusneh.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shishusneh.app.db.entity.GrowthEntry

@Dao
interface GrowthDao {
    @Query("SELECT * FROM growth_entry ORDER BY dateMillis ASC")
    fun observeAll(): LiveData<List<GrowthEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: GrowthEntry): Long

    @Delete
    suspend fun delete(entry: GrowthEntry)
}
