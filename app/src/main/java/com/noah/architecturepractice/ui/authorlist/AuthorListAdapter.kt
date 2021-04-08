package com.noah.architecturepractice.ui.authorlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noah.architecturepractice.databinding.ItemAuthorBinding
import com.noah.architecturepractice.model.Author

class AuthorListAdapter(private val authors: List<Author>) : RecyclerView.Adapter<AuthorListAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemAuthorBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(authors[position])
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    inner class ViewHolder(val binding: ItemAuthorBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    if (adapterPosition != RecyclerView.NO_POSITION)
                        (onItemClickListener as OnItemClickListener).onItemClick(itemView, adapterPosition)
                }
            }
        }

        fun bind(author: Author) {
            binding.author = author
            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(itemView: View, position: Int)
    }
}