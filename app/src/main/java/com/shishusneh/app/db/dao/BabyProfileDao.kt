package com.shishusneh.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shishusneh.app.db.entity.BabyProfile

@Dao
interface BabyProfileDao {
    @Query("SELECT * FROM baby_profile WHERE id = 1 LIMIT 1")
    fun observe(): LiveData<BabyProfile?>

    @Query("SELECT * FROM baby_profile WHERE id = 1 LIMIT 1")
    suspend fun get(): BabyProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(profile: BabyProfile)

    @Query("DELETE FROM baby_profile")
    suspend fun clear()
}
