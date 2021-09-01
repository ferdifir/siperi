package com.siperi.service.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siperi.databinding.ItemCatalogBinding
import com.siperi.service.data.source.local.entity.CatalogEntity

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private var listData = ArrayList<CatalogEntity>()
    var onItemClick: ((CatalogEntity) -> Unit)? = null

    inner class CatalogViewHolder(private val binding: ItemCatalogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookCatalog: CatalogEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(bookCatalog.imgPreview)
                    .into(ivCover)

                tvTitle.text = bookCatalog.title
                tvCategory.text = bookCatalog.category
            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    fun setData(newListData: List<CatalogEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}