package com.mvvm_hilt_coroutine.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_hilt_coroutine.utils.AppHelper
import com.photopicker.databinding.ItemProductDetailsBinding

class ProductDetailsAdapter(private val dataList: List<String?>) :
    RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductDetailsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = dataList[position]
        model?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val itemBinding: ItemProductDetailsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: String) {
            AppHelper.loadImage(model,itemBinding.photoImage,false)
        }
    }
}