package com.shishusneh.app.ui.vaccines

import android.app.Application
import androidx.lifecycle.*
import com.shishusneh.app.data.Repository
import com.shishusneh.app.data.VaccineEvent
import com.shishusneh.app.data.VaccineScheduleBuilder
import com.shishusneh.app.db.entity.BabyProfile
import com.shishusneh.app.db.entity.VaccineRecord
import kotlinx.coroutines.launch

class VaccinesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app)

    private val profile: LiveData<BabyProfile?> = repo.profileDao.observe()
    private val records: LiveData<List<VaccineRecord>> = repo.vaccineDao.observeAll()

    val schedule: LiveData<List<VaccineEvent>> = MediatorLiveData<List<VaccineEvent>>().apply {
        val update = {
            val p = profile.value
            val r = records.value ?: emptyList()
            value = if (p == null) emptyList() else VaccineScheduleBuilder.build(p.dobMillis, r)
        }
        addSource(profile) { update() }
        addSource(records) { update() }
    }

    fun toggle(id: String, completed: Boolean) {
        viewModelScope.launch { repo.setVaccineCompleted(id, completed) }
    }
}
