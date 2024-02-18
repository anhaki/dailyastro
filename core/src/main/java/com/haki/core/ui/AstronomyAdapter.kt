package com.haki.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haki.core.R
import com.haki.core.databinding.DailyItemBinding
import com.haki.core.domain.model.Astronomy

class AstronomyAdapter : RecyclerView.Adapter<AstronomyAdapter.ListViewHolder>() {

    private var listData = ArrayList<Astronomy>()
    var onItemClick: ((Astronomy) -> Unit)? = null

    fun setData(newListData: List<Astronomy>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.daily_item, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DailyItemBinding.bind(itemView)
        fun bind(data: Astronomy) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url)
                    .into(ivPhoto)
                tvName.text = data.title
                Log.d("yahhh", data.title)
                tvDate.text = data.date
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}