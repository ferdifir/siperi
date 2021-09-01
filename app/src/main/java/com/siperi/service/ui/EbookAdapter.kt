package com.siperi.service.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siperi.databinding.ItemEbookBinding
import com.siperi.service.data.source.local.entity.EbookEntity

class EbookAdapter: RecyclerView.Adapter<EbookAdapter.EbookViewHolder>() {

    private var listData = ArrayList<EbookEntity>()
    var onItemClick: ((EbookEntity) -> Unit)? = null

    inner class EbookViewHolder(private val binding: ItemEbookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ebook: EbookEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(ebook.image)
                    .into(ivCover)

                tvTitle.text = ebook.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    fun setData(newListData: List<EbookEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        val binding = ItemEbookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EbookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EbookAdapter.EbookViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}