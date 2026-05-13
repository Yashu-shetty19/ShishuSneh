package com.shishusneh.app.ui.growth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shishusneh.app.data.Repository
import com.shishusneh.app.db.entity.GrowthEntry
import kotlinx.coroutines.launch

class GrowthViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app)
    val entries: LiveData<List<GrowthEntry>> = repo.growthDao.observeAll()

    fun add(dateMillis: Long, weight: Float, height: Float) {
        viewModelScope.launch { repo.addGrowth(GrowthEntry(0, dateMillis, weight, height)) }
    }

    fun delete(entry: GrowthEntry) {
        viewModelScope.launch { repo.deleteGrowth(entry) }
    }
}
