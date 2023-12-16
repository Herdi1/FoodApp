package com.abid.submissionapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.abid.submissionapp.databinding.ItemRowAppBinding

class ListFoodAdapter(private val listFood: ArrayList<Food>) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowAppBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, img) = listFood[position]
        holder.binding.imageItem.setImageResource(img)
        holder.binding.titleItem.text = name
        holder.binding.descriptionItem.text = description

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])}
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Food)
    }
}