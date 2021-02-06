package com.example.newscroll.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.Glide.GlideApp
import com.example.newscroll.R
import com.example.newscroll.Room.LikeNews
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_dibs.view.*
import kotlinx.android.synthetic.main.item_news.view.ivThumbnail
import kotlinx.android.synthetic.main.item_news.view.tvDescription
import kotlinx.android.synthetic.main.item_news.view.tvTitle

class LikeNewsAdapter(val onDeleteClick : (LikeNews) -> Unit): RecyclerView.Adapter<LikeNewsAdapter.LikeNewsViewHolder>() {

    private var newsList : List<LikeNews> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_dibs, parent, false)

        return LikeNewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LikeNewsViewHolder, position: Int) {
        val news = newsList[position]

        holder.bind(news)
    }

    inner class LikeNewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: LikeNews) {
            itemView.btnDelete.setOnClickListener {
                onDeleteClick(news)
                Snackbar.make(it, "해당 기사가 목록에서 지워졌습니다.", Snackbar.LENGTH_SHORT).show()
            }
            itemView.apply {
                GlideApp.with(ivThumbnail)
                        .load(news.thumbnailUrl)
                        .placeholder(R.drawable.ic_dashboard_black_24dp)
                        .into(ivThumbnail)
                tvTitle.text = news.title
                tvDescription.text = news.description
            }
        }

    }

    override fun getItemCount(): Int = newsList.size

    fun setNewsList(news: List<LikeNews>) {
        this.newsList = news
        notifyDataSetChanged()
    }

}