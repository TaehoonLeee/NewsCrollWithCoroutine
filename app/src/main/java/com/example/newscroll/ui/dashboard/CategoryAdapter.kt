package com.example.newscroll.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter : ListAdapter<String, CategoryAdapter.CategoryViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val cast = getItem(position)
        holder.bind(cast)
    }

    class CategoryViewHolder private constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: String) {
            itemView.apply {
                btnCategory.text = category
            }
        }

        companion object {
            fun from(parent : ViewGroup) : CategoryViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_category, parent, false)
                return CategoryViewHolder(itemView)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        }
    }
}