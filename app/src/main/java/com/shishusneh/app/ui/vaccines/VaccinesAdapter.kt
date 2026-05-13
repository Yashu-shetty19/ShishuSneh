package com.shishusneh.app.ui.vaccines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shishusneh.app.R
import com.shishusneh.app.data.VaccineEvent
import com.shishusneh.app.databinding.ItemVaccineBinding

class VaccinesAdapter(
    private val onToggle: (String, Boolean) -> Unit
) : RecyclerView.Adapter<VaccinesAdapter.VH>() {

    private val items = mutableListOf<VaccineEvent>()

    fun submit(list: List<VaccineEvent>) {
        items.clear(); items.addAll(list); notifyDataSetChanged()
    }

    class VH(val b: ItemVaccineBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemVaccineBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val v = items[position]
        val ctx = holder.itemView.context
        holder.b.name.text = v.name
        holder.b.meta.text = "${v.ageLabel} · ${v.dueDateFormatted()}"
        holder.b.prevents.text = "🛡️ Prevents ${v.prevents}"
        val (label, colorRes) = when (v.status) {
            VaccineEvent.Status.COMPLETED -> "✅ Completed" to R.color.mint
            VaccineEvent.Status.DUE_SOON -> "🟡 Due soon" to R.color.warm
            VaccineEvent.Status.OVERDUE -> "🔴 Overdue" to R.color.danger
            VaccineEvent.Status.UPCOMING -> "🟢 Upcoming" to R.color.sky
        }
        holder.b.statusChip.text = label
        holder.b.statusChip.setBackgroundColor(ContextCompat.getColor(ctx, colorRes))

        holder.b.checkBtn.isChecked = v.status == VaccineEvent.Status.COMPLETED
        holder.b.checkBtn.setOnClickListener {
            onToggle(v.id, v.status != VaccineEvent.Status.COMPLETED)
        }
    }

    override fun getItemCount() = items.size
}
