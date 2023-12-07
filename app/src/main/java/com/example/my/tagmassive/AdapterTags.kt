package com.example.my.tagmassive

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my.tagmassive.databinding.ItemTagsBinding

class AdapterTags(val onItemClick: (tags: String) -> Unit) :
    RecyclerView.Adapter<AdapterTags.ViewHolder>() {
    private val tags = arrayListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: ArrayList<String>) {
        tags.clear()
        tags.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int = tags.size

    inner class ViewHolder(private var binding: ItemTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String) {
            binding.tvHint.text = tag
            itemView.setOnClickListener {
                onItemClick(tag)
            }
        }
    }

}