package com.example.story.helper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.story.data.Response.ListStoryItem
import com.example.story.databinding.CardstoryBinding
import com.example.story.view.detail.DetailActivity

class AdapterList(private val listStory: List<ListStoryItem>) :
    RecyclerView.Adapter<AdapterList.Viewholder>() {
    class Viewholder(private val binding: CardstoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: ListStoryItem) {
            binding.nama.text = story.name
            Glide.with(binding.root)
                .load(story.photoUrl)
                .into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = CardstoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int {
        return listStory.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(listStory[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, listStory[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }
}