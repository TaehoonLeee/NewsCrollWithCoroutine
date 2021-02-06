package com.example.newscroll.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newscroll.Glide.GlideApp
import com.example.newscroll.R
import com.example.newscroll.ui.CategoryNews.CategoryNewsFragmentDirections
import com.example.newscroll.ui.home.HomeFragmentDirections
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_news.view.*

data class News(
    val platForm : String,
    val thumbnailUrl : String,
    val title : String,
    val description : String,
    val url : String?
)

class NewsAdapter(val onFavoriteClick : (News) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList : List<News> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
//        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    inner class NewsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: News) {
            itemView.swipeView.setOnClickListener {
                lateinit var direction : NavDirections
                when(news.platForm) {
                    "naver" -> {
                        direction = DashboardFragmentDirections.actionNavigationHomeToNewsContentFragment(news.url!!)
                    }
                    "daum" -> {
                        direction = HomeFragmentDirections.actionNavigationDashboardToNewsContentFragment(news.url!!)
                    }
                    "category" -> {
                        direction = CategoryNewsFragmentDirections.actionCategoryNewsFragmentToNewsContentFragment(news.url!!)
                    }
                }
                it.findNavController().navigate(direction)
            }
            itemView.btnFavorite.setOnClickListener {
                onFavoriteClick(news)
                Snackbar.make(it, "해당 기사가 즐겨찾기 됐습니다.", Snackbar.LENGTH_SHORT).show()
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

//        companion object {
//            fun from(parent: ViewGroup) : NewsViewHolder {
//                val inflater = LayoutInflater.from(parent.context)
//                val itemView = inflater.inflate(R.layout.item_news, parent, false)
//
//                return NewsViewHolder(itemView)
//            }
//        }
    }

    override fun getItemCount(): Int = newsList.size

    fun setNewsList(news: List<News>) {
        this.newsList = news
        notifyDataSetChanged()
    }

    fun getNews(index : Int) : News {
        return newsList.get(index)
    }

}