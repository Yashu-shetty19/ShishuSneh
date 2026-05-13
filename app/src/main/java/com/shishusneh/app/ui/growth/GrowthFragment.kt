package com.shishusneh.app.ui.growth

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.shishusneh.app.databinding.FragmentGrowthBinding
import com.shishusneh.app.util.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class GrowthFragment : Fragment() {
    private var _binding: FragmentGrowthBinding? = null
    private val binding get() = _binding!!
    private val vm: GrowthViewModel by viewModels()
    private lateinit var adapter: GrowthAdapter
    private val display = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    private var pickedDate = System.currentTimeMillis()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentGrowthBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GrowthAdapter { vm.delete(it) }
        binding.historyList.layoutManager = LinearLayoutManager(requireContext())
        binding.historyList.adapter = adapter

        binding.dateButton.text = display.format(Date(pickedDate))
        binding.dateButton.setOnClickListener {
            val cal = Calendar.getInstance().apply { timeInMillis = pickedDate }
            DatePickerDialog(requireContext(), { _, y, m, d ->
                cal.set(y, m, d, 0, 0, 0); cal.set(Calendar.MILLISECOND, 0)
                pickedDate = cal.timeInMillis
                binding.dateButton.text = display.format(Date(pickedDate))
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                .apply { datePicker.maxDate = System.currentTimeMillis() }.show()
        }

        binding.saveButton.setOnClickListener {
            val w = binding.weightInput.text?.toString()?.toFloatOrNull()
            val h = binding.heightInput.text?.toString()?.toFloatOrNull()
            if (w == null || h == null) { binding.statusText.text = "Please enter weight & height"; return@setOnClickListener }
            vm.add(pickedDate, w, h)
            binding.weightInput.text?.clear(); binding.heightInput.text?.clear()
        }

        vm.entries.observe(viewLifecycleOwner) { list ->
            adapter.submit(list.reversed())
            renderChart(list)
            binding.statusText.text = when {
                list.size < 2 -> "Add monthly entries to see trend 🌷"
                list.last().weightKg >= list[list.size - 2].weightKg -> "Growing well 🌱  Keep it up Amma!"
                else -> "Slight dip — please consult ASHA didi ⚠️"
            }
        }
    }

    private fun renderChart(list: List<com.shishusneh.app.db.entity.GrowthEntry>) {
        if (list.isEmpty()) {
            binding.lineChart.clear(); binding.lineChart.invalidate(); return
        }
        val labels = list.map { DateUtils.monthLabel(it.dateMillis) }
        val weight = list.mapIndexed { i, e -> Entry(i.toFloat(), e.weightKg) }
        val height = list.mapIndexed { i, e -> Entry(i.toFloat(), e.heightCm) }

        val ws = LineDataSet(weight, "Weight (kg)").apply {
            color = Color.parseColor("#E8588A"); setCircleColor(color); lineWidth = 3f; circleRadius = 4f; setDrawValues(false); mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        val hs = LineDataSet(height, "Height (cm)").apply {
            color = Color.parseColor("#5B8DEF"); setCircleColor(color); lineWidth = 3f; circleRadius = 4f; setDrawValues(false); mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        binding.lineChart.apply {
            data = LineData(ws, hs)
            description = Description().apply { text = "" }
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            xAxis.granularity = 1f
            axisRight.isEnabled = false
            invalidate()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
