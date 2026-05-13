package com.shishusneh.app.ui.milestones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shishusneh.app.data.MilestoneCatalog
import com.shishusneh.app.databinding.FragmentMilestonesBinding

class MilestonesFragment : Fragment() {
    private var _binding: FragmentMilestonesBinding? = null
    private val binding get() = _binding!!
    private val vm: MilestonesViewModel by viewModels()
    private lateinit var adapter: MilestonesAdapter

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentMilestonesBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MilestonesAdapter { id, reached -> vm.toggle(id, reached) }
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter

        vm.records.observe(viewLifecycleOwner) { records ->
            val map = records.associateBy { it.milestoneId }
            adapter.submit(MilestoneCatalog.MILESTONES, map)
            val total = MilestoneCatalog.MILESTONES.size
            val done = records.count { it.reached }
            val pct = if (total == 0) 0 else (done * 100 / total)
            binding.progressBar.progress = pct
            binding.progressText.text = "$pct%"
            binding.summary.text = "$done/$total milestones reached"
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
