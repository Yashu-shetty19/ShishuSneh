package com.shishusneh.app.ui.vaccines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shishusneh.app.databinding.FragmentVaccinesBinding

class VaccinesFragment : Fragment() {
    private var _binding: FragmentVaccinesBinding? = null
    private val binding get() = _binding!!
    private val vm: VaccinesViewModel by viewModels()
    private lateinit var adapter: VaccinesAdapter

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentVaccinesBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = VaccinesAdapter { id, completed -> vm.toggle(id, completed) }
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter

        vm.schedule.observe(viewLifecycleOwner) { list ->
            adapter.submit(list)
            val done = list.count { it.status == com.shishusneh.app.data.VaccineEvent.Status.COMPLETED }
            binding.summary.text = "$done of ${list.size} completed"
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
