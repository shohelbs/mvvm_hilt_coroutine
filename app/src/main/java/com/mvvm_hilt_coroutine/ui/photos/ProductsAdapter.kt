package com.mvvm_hilt_coroutine.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_hilt_coroutine.model.Product
import com.mvvm_hilt_coroutine.utils.AppHelper
import com.photopicker.databinding.ItemProductBinding

class ProductsAdapter(
    private var photos: List<Product?>,
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val item = photos[position]
            item?.let {
                AppHelper.loadImage(it.thumbnail, binding.photoImage, false)
                binding.photoTitle.text = it.title
            }
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}