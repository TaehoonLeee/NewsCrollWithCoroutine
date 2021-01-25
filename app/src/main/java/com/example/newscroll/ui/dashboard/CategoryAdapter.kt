package com.example.newscroll.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.R
import kotlinx.android.synthetic.main.item_category.view.*

data class Category(
    val title : String,
    val url : String?
)

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val cast = getItem(position)
        holder.bind(cast)
    }

    class CategoryViewHolder private constructor(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, category.url, Toast.LENGTH_LONG).show()
            }
            itemView.apply {
                btnCategory.text = category.title
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
        private val COMPARATOR = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem
        }
    }
}