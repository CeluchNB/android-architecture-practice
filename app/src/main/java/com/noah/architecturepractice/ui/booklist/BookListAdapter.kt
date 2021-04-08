package com.noah.architecturepractice.ui.booklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noah.architecturepractice.databinding.ItemBookBinding
import com.noah.architecturepractice.model.Title

class BookListAdapter(private val titles: List<Title>) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemBookBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(titles[position])
    }

    inner class ViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        (onItemClickListener as OnItemClickListener).onItemClicked(itemView, adapterPosition)
                    }
                }
            }
        }

        fun bind(title: Title) {
            binding.book = title
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(itemView: View, position: Int)
    }
}