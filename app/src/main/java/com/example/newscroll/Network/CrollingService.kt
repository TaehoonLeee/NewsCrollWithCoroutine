package com.example.newscroll.Network

import android.util.Log
import com.example.newscroll.model.Result
import com.example.newscroll.ui.dashboard.Category
import com.example.newscroll.ui.dashboard.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

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

    fun getCategory(url: String, query: String) : Result<List<Category>> {
        val doc : Document
        val category : List<String>
        val categoryUrl : List<String>
        try {
            doc = Jsoup.connect(url).get()
            category = doc.select(query).textNodes().joinToString(",").split(" ,")
            categoryUrl = doc.select(query).eachAttr("href")
        } catch (e : Exception) { return Result.Error(e.message!!) }

        val categoryList : MutableList<Category> = mutableListOf()
        repeat(category.size) {
            categoryList.add(it, Category(category.get(it), "https://news.naver.com" + categoryUrl.get(it)))
        }

        return Result.Success(categoryList)
    }

    fun getNewsList() : Result<List<News>> {
        val doc : Document
        val thumbNailUrlList : List<String>
        val HeadLineList : List<Element>
        try {
            doc =
                Jsoup.connect("https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105")
                    .maxBodySize(0).get()
            thumbNailUrlList = doc.select("li.cluster_item").select("img").eachAttr("src")
            HeadLineList = doc.select("li.cluster_item") - doc.select("li.cluster_item.as_line")
        } catch( e : Exception) { return Result.Error(e.message!!) }

        val newsList : MutableList<News> = mutableListOf()
        repeat(thumbNailUrlList.size) {
            newsList.add(it, News(
                thumbNailUrlList.get(it),
                HeadLineList.get(it).select("a").text(),
                HeadLineList.get(it).select("div.cluster_text_lede").text()))
        }

        return Result.Success(newsList)
    }
}