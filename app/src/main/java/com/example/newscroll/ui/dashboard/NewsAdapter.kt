package com.example.newscroll.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.Glide.GlideApp
import com.example.newscroll.R
import kotlinx.android.synthetic.main.item_news.view.*

data class News(
    val thumbnailUrl : String,
    val title : String,
    val description : String
)

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList : List<News> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: News) {
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
            fun from(parent: ViewGroup) : NewsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_news, parent, false)

                return NewsViewHolder(itemView)
            }
        }

    }

    override fun getItemCount(): Int = newsList.size

    fun setNewsList(news: List<News>) {
        this.newsList = news
        notifyDataSetChanged()
    }

}