package com.shishusneh.app.ui.growth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shishusneh.app.databinding.ItemGrowthBinding
import com.shishusneh.app.db.entity.GrowthEntry
import com.shishusneh.app.util.DateUtils

class GrowthAdapter(private val onDelete: (GrowthEntry) -> Unit) :
    RecyclerView.Adapter<GrowthAdapter.VH>() {

    private val items = mutableListOf<GrowthEntry>()

    fun submit(list: List<GrowthEntry>) {
        items.clear(); items.addAll(list); notifyDataSetChanged()
    }

    class VH(val b: ItemGrowthBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemGrowthBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        holder.b.title.text = "${e.weightKg} kg · ${e.heightCm} cm"
        holder.b.subtitle.text = DateUtils.format(e.dateMillis)
        holder.b.deleteBtn.setOnClickListener { onDelete(e) }
    }

    override fun getItemCount() = items.size
}
