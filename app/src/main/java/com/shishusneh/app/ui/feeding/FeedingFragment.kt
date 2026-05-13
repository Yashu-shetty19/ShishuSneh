package com.shishusneh.app.ui.feeding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.shishusneh.app.data.FeedingTips
import com.shishusneh.app.databinding.FragmentFeedingBinding

class FeedingFragment : Fragment() {
    private var _binding: FragmentFeedingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentFeedingBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tips = FeedingTips.TIPS
        val adapter = FeedingAdapter(tips)
        binding.pager.adapter = adapter
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicator.text = "Tip 1 of ${tips.size}"

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.indicator.text = "Tip ${position + 1} of ${tips.size}"
            }
        })

        binding.prevBtn.setOnClickListener {
            val cur = binding.pager.currentItem
            binding.pager.currentItem = if (cur > 0) cur - 1 else tips.size - 1
        }
        binding.nextBtn.setOnClickListener {
            val cur = binding.pager.currentItem
            binding.pager.currentItem = if (cur < tips.size - 1) cur + 1 else 0
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
