package com.haki.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haki.core.R
import com.haki.core.databinding.DailyItemBinding
import com.haki.core.domain.model.Astronomy
import java.text.SimpleDateFormat
import java.util.Locale

class AstronomyAdapter : RecyclerView.Adapter<AstronomyAdapter.ListViewHolder>() {

    private var listData = ArrayList<Astronomy>()
    var onItemClick: ((Astronomy) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Astronomy>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.daily_item, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DailyItemBinding.bind(itemView)
        fun bind(data: Astronomy) {
            val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val simpleDateFormat2 = SimpleDateFormat("dd MMMM yyyy", Locale.US)

            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url)
                    .into(ivPhoto)
                tvName.text = data.title
                tvDate.text = simpleDateFormat2.format(simpleDateFormat1.parse(data.date) ?: "")
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}