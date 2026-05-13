package com.shishusneh.app.ui.onboarding

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.shishusneh.app.databinding.ActivityOnboardingBinding
import com.shishusneh.app.ui.dashboard.MainActivity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val vm: OnboardingViewModel by viewModels()
    private var dobMillis: Long = 0L
    private var gender: String? = null
    private val display = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If profile already exists, skip onboarding
        lifecycleScope.launch {
            vm.existingProfile()?.let { goToDashboard() }
        }

        binding.dobButton.setOnClickListener { showDatePicker() }

        binding.genderGirl.setOnClickListener { selectGender("girl") }
        binding.genderBoy.setOnClickListener { selectGender("boy") }
        binding.genderOther.setOnClickListener { selectGender("other") }

        binding.continueButton.setOnClickListener {
            val name = binding.nameInput.text?.toString()?.trim().orEmpty()
            if (name.isEmpty()) { binding.nameLayout.error = "Please enter baby's name"; return@setOnClickListener }
            if (dobMillis == 0L) { binding.dobError.text = "Please pick date of birth"; return@setOnClickListener }
            binding.nameLayout.error = null
            binding.dobError.text = ""
            vm.saveProfile(name, dobMillis, gender) { goToDashboard() }
        }
    }

    private fun selectGender(g: String) {
        gender = g
        binding.genderGirl.isChecked = g == "girl"
        binding.genderBoy.isChecked = g == "boy"
        binding.genderOther.isChecked = g == "other"
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, y, m, d ->
                cal.set(y, m, d, 0, 0, 0)
                cal.set(Calendar.MILLISECOND, 0)
                dobMillis = cal.timeInMillis
                binding.dobButton.text = display.format(Date(dobMillis))
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
            show()
        }
    }

    private fun goToDashboard() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
