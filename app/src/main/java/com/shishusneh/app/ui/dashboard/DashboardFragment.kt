package com.shishusneh.app.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.shishusneh.app.R
import com.shishusneh.app.data.MilestoneCatalog
import com.shishusneh.app.databinding.FragmentDashboardBinding
import com.shishusneh.app.util.DateUtils

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val vm: DashboardViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.profile.observe(viewLifecycleOwner) { p ->
            p ?: return@observe
            val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
            val part = when { hour < 12 -> "Good morning"; hour < 17 -> "Good afternoon"; else -> "Good evening" }
            binding.greeting.text = "$part Amma 👋"
            binding.babyName.text = "Your sneh for ${p.name} 💗"
            binding.babyAge.text = "${DateUtils.ageLabel(p.dobMillis)} · Healthy & loved 🌸"
            binding.profileName.text = p.name
            binding.profileDob.text = "Born ${DateUtils.format(p.dobMillis)}"
        }

        vm.nextVaccine.observe(viewLifecycleOwner) { v ->
            if (v == null) {
                binding.nextVaccineName.text = "All vaccines done 🎉"
                binding.nextVaccineMeta.text = ""
            } else {
                binding.nextVaccineName.text = v.name
                binding.nextVaccineMeta.text = "Due ${v.dueDateFormatted()} · prevents ${v.prevents}"
            }
        }

        vm.growth.observe(viewLifecycleOwner) { entries ->
            val data = if (entries.isEmpty()) {
                listOf(3f, 3.6f, 4.4f, 5.2f, 6.0f, 6.8f)
            } else entries.takeLast(6).map { it.weightKg }
            renderMiniChart(data)
            binding.growthLatest.text =
                if (entries.isEmpty()) "—" else "${entries.last().weightKg} kg"
        }

        vm.milestones.observe(viewLifecycleOwner) { records ->
            val total = MilestoneCatalog.MILESTONES.size
            val done = records.count { it.reached }
            val pct = if (total == 0) 0 else (done * 100 / total)
            binding.milestoneProgress.progress = pct
            binding.milestonePctText.text = "$pct%"
            binding.milestoneSubtext.text = "$done/$total reached"
        }

        binding.vaccineCard.setOnClickListener {
            findNavController().navigate(R.id.vaccinesFragment)
        }
        binding.fabAddWeight.setOnClickListener {
            findNavController().navigate(R.id.growthFragment)
        }
    }

    private fun renderMiniChart(values: List<Float>) {
        val entries = values.mapIndexed { i, v -> Entry(i.toFloat(), v) }
        val set = LineDataSet(entries, "Weight").apply {
            color = Color.parseColor("#5B8DEF")
            setDrawCircles(false)
            lineWidth = 2.4f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        binding.miniChart.apply {
            data = LineData(set)
            description = Description().apply { text = "" }
            legend.isEnabled = false
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            setTouchEnabled(false)
            invalidate()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
