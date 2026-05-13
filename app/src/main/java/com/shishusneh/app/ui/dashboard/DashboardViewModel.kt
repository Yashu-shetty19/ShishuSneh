package com.shishusneh.app.ui.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.shishusneh.app.data.Repository
import com.shishusneh.app.data.VaccineEvent
import com.shishusneh.app.data.VaccineScheduleBuilder
import com.shishusneh.app.db.entity.BabyProfile
import com.shishusneh.app.db.entity.GrowthEntry
import com.shishusneh.app.db.entity.MilestoneRecord
import com.shishusneh.app.db.entity.VaccineRecord

class DashboardViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app)

    val profile: LiveData<BabyProfile?> = repo.profileDao.observe()
    val growth: LiveData<List<GrowthEntry>> = repo.growthDao.observeAll()
    private val vaccineRecords: LiveData<List<VaccineRecord>> = repo.vaccineDao.observeAll()
    val milestones: LiveData<List<MilestoneRecord>> = repo.milestoneDao.observeAll()

    val nextVaccine: LiveData<VaccineEvent?> = MediatorLiveData<VaccineEvent?>().apply {
        val update = {
            val p = profile.value
            val rec = vaccineRecords.value ?: emptyList()
            value = p?.let {
                VaccineScheduleBuilder.build(it.dobMillis, rec)
                    .filter { v -> v.status != VaccineEvent.Status.COMPLETED }
                    .minByOrNull { v -> v.dueDateMillis }
            }
        }
        addSource(profile) { update() }
        addSource(vaccineRecords) { update() }
    }
}
