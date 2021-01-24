package com.example.newscroll.Network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.newscroll.ui.dashboard.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class CrollingService {

    fun getCategory1(url : String, query : String) : List<String>{
        suspend fun getResult() : List<String> {
            var category : List<String> = ArrayList()

//            val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
            val doc = Jsoup.connect(url).timeout(10000).get()

//            category = doc.select("ul.nav").text().split(" ")
            category = doc.select(query).text().split(" ")

            Log.e("Inner service", category.get(0))

            return category
        }

        var category : List<String> = ArrayList()

        CoroutineScope(IO).launch {
            Log.e("service in coroutine", "test1")
            category = getResult()
        }

        Log.e("service", "test")

        return category
    }

    fun getCategory(url: String, query: String) : List<String> {
//        val url2 = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
        val doc = Jsoup.connect(url).timeout(10000).get()
//        val category = doc.select("ul.nav").text().split(" ")
        val category = doc.select(query).text().split(" ")
        Log.e("Inner service", category.get(0))

        return category
    }

    fun getNewsList() : List<News> {
        val doc = Jsoup.connect("https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105").maxBodySize(0).get()
        val thumbNailUrlList = doc.select("li.cluster_item").select("img").eachAttr("src")
        val HeadLineList = doc.select("li.cluster_item") - doc.select("li.cluster_item.as_line")

        val newsList : MutableList<News> = mutableListOf()
        repeat(thumbNailUrlList.size) {
            newsList.add(it, News(
                thumbNailUrlList.get(it),
                HeadLineList.get(it).select("a").text(),
                HeadLineList.get(it).select("div.cluster_text_lede").text()))
        }

        return newsList
    }
}