package com.shishusneh.app.ui.onboarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shishusneh.app.data.Repository
import com.shishusneh.app.db.entity.BabyProfile
import com.shishusneh.app.worker.VaccinationReminderWorker
import kotlinx.coroutines.launch

class OnboardingViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app)

    suspend fun existingProfile() = repo.getProfile()

    fun saveProfile(name: String, dobMillis: Long, gender: String?, onSaved: () -> Unit) {
        viewModelScope.launch {
            repo.saveProfile(BabyProfile(1, name, dobMillis, gender))
            VaccinationReminderWorker.scheduleAll(getApplication(), dobMillis)
            onSaved()
        }
    }
}
