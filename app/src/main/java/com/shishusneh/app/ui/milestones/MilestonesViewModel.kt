package com.shishusneh.app.ui.milestones

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shishusneh.app.data.Repository
import com.shishusneh.app.db.entity.MilestoneRecord
import kotlinx.coroutines.launch

class MilestonesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app)
    val records: LiveData<List<MilestoneRecord>> = repo.milestoneDao.observeAll()

    fun toggle(id: String, reached: Boolean) {
        viewModelScope.launch { repo.setMilestone(id, reached) }
    }
}
