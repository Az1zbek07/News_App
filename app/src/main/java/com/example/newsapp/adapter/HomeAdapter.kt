package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Inform
import com.example.newsapp.databinding.RemoteNewLayoutBinding

class HomeAdapter: ListAdapter<Inform, HomeAdapter.VHolder>(DiffCallback()) {
    lateinit var onClick: (Inform) -> Unit
    lateinit var onSave: (Inform) -> Unit
    private class DiffCallback: DiffUtil.ItemCallback<Inform>(){
        override fun areItemsTheSame(oldItem: Inform, newItem: Inform): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Inform, newItem: Inform): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(RemoteNewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: RemoteNewLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(inform: Inform){
            binding.textView.text = inform.title
            if (inform.url != null){
                with(binding){
                    Glide.with(imageView).load(inform.url).into(imageView)
                }
            }

            itemView.setOnClickListener {
                onClick(inform)
            }

            binding.btnSave.setOnClickListener {
                onSave(inform)
            }
        }
    }
}