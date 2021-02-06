package com.example.newscroll.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.Glide.GlideApp
import com.example.newscroll.R
import com.example.newscroll.Room.LikeNews
import kotlinx.android.synthetic.main.item_news.view.*

class LikeNewsAdapter: RecyclerView.Adapter<LikeNewsAdapter.LikeNewsViewHolder>() {

    private var newsList : List<LikeNews> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeNewsViewHolder {
        return LikeNewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LikeNewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    class LikeNewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: LikeNews) {
            itemView.apply {
                GlideApp.with(ivThumbnail)
                        .load(news.thumbnailUrl)
                        .placeholder(R.drawable.ic_dashboard_black_24dp)
                        .into(ivThumbnail)
                tvTitle.text = news.title
                tvDescription.text = news.description
            }
        }


        companion object {
            fun from(parent: ViewGroup) : LikeNewsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_news, parent, false)

                return LikeNewsViewHolder(itemView)
            }
        }

    }

    override fun getItemCount(): Int = newsList.size

    fun setNewsList(news: List<LikeNews>) {
        this.newsList = news
        notifyDataSetChanged()
    }

}