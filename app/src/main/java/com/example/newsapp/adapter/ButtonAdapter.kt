package com.example.newsapp.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.SplScrButton
import com.example.newsapp.R
import com.example.newsapp.databinding.ButtonItemLayoutBinding
import dagger.hilt.android.qualifiers.ApplicationContext

class ButtonAdapter(private val context: Context): ListAdapter<SplScrButton, ButtonAdapter.VHolder>(DiffCallback()) {
    private class DiffCallback: DiffUtil.ItemCallback<SplScrButton>(){
        override fun areItemsTheSame(oldItem: SplScrButton, newItem: SplScrButton): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: SplScrButton, newItem: SplScrButton): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(ButtonItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: ButtonItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(splScrButton: SplScrButton){
            binding.imageView.setImageResource(splScrButton.image)
            binding.textView.text = splScrButton.text
            var isEnabled = false

            itemView.setOnClickListener {
                if (!isEnabled){
                    binding.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.main_color))
                }else{
                    binding.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
                }

                isEnabled = !isEnabled
            }
        }
    }
}