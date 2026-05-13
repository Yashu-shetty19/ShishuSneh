package com.shishusneh.app.ui.feeding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shishusneh.app.data.FeedingTips
import com.shishusneh.app.databinding.ItemFeedingTipBinding

class FeedingAdapter(private val tips: List<FeedingTips.Tip>) :
    RecyclerView.Adapter<FeedingAdapter.VH>() {

    class VH(val b: ItemFeedingTipBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemFeedingTipBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val t = tips[position]
        holder.b.emoji.text = t.emoji
        holder.b.title.text = t.title
        holder.b.body.text = t.body
    }

    override fun getItemCount() = tips.size
}
