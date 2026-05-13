package com.shishusneh.app.ui.milestones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shishusneh.app.data.MilestoneCatalog
import com.shishusneh.app.databinding.ItemMilestoneBinding
import com.shishusneh.app.db.entity.MilestoneRecord

class MilestonesAdapter(
    private val onToggle: (String, Boolean) -> Unit
) : RecyclerView.Adapter<MilestonesAdapter.VH>() {

    private val items = mutableListOf<MilestoneCatalog.MilestoneDef>()
    private var stateMap: Map<String, MilestoneRecord> = emptyMap()

    fun submit(defs: List<MilestoneCatalog.MilestoneDef>, map: Map<String, MilestoneRecord>) {
        items.clear(); items.addAll(defs); stateMap = map; notifyDataSetChanged()
    }

    class VH(val b: ItemMilestoneBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemMilestoneBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val m = items[position]
        val reached = stateMap[m.id]?.reached == true
        holder.b.emoji.text = m.emoji
        holder.b.title.text = m.title
        holder.b.subtitle.text = if (m.ageWeeks < 52) "Around ${m.ageWeeks} weeks" else "Around 1 year"
        holder.b.checkbox.setOnCheckedChangeListener(null)
        holder.b.checkbox.isChecked = reached
        holder.b.checkbox.setOnCheckedChangeListener { _, isChecked -> onToggle(m.id, isChecked) }
        holder.itemView.setOnClickListener { holder.b.checkbox.isChecked = !holder.b.checkbox.isChecked }
    }

    override fun getItemCount() = items.size
}
