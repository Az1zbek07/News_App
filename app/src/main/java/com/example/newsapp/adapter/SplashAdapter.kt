package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.SplScrImage
import com.example.newsapp.databinding.ImageItemLayoutBinding

class SplashAdapter: ListAdapter<SplScrImage, SplashAdapter.VHolder>(DiffCallback()) {
    private class DiffCallback: DiffUtil.ItemCallback<SplScrImage>(){
        override fun areItemsTheSame(oldItem: SplScrImage, newItem: SplScrImage): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: SplScrImage, newItem: SplScrImage): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(ImageItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: ImageItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(splScrImage: SplScrImage){
            binding.imageView.setImageResource(splScrImage.image)
        }
    }
}