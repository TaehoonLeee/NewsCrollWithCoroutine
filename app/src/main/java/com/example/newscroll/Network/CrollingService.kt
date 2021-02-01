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
                platForm = "naver",
                thumbnailUrl = thumbNailUrlList.get(it),
                title = HeadLineList.get(it).select("a").text(),
                description = HeadLineList.get(it).select("div.cluster_text_lede").text(),
                url = HeadLineList.get(it).select("a").attr("href")))
        }

        return Result.Success(newsList)
    }

    fun getDaumNewsList() : Result<List<News>> {
        val doc : Document
        val thumbnailUrlList : List<String>
        val titleList : List<String>
        val descriptionList : List<String>
        val urlList : MutableList<String> = mutableListOf()
        try {
            doc =
                Jsoup.connect("https://news.daum.net/digital#1").maxBodySize(0).get()
            thumbnailUrlList = doc.select("div.section_cate.section_headline").select("img").eachAttr("src")
            titleList = doc.select("div.section_cate.section_headline").select("img").eachAttr("alt")
            descriptionList = doc.select("p.desc_thumb").eachText()

            val headLineUrl = doc.select("div.item_mainnews").select("a").attr("href")
            val listNewsUrlList = doc.select("ul.list_mainnews").select("a").eachAttr("href")
            urlList.add(headLineUrl)
            repeat( (listNewsUrlList.size) / 2 ) {
                urlList.add(listNewsUrlList.get(it*2))
            }

        } catch (e : Exception) { return  Result.Error(e.message!!) }

        val newsList : MutableList<News> = mutableListOf()
        repeat(thumbnailUrlList.size) {
            newsList.add(it, News(
                platForm = "daum",
                thumbnailUrl = thumbnailUrlList.get(it),
                title = titleList.get(it),
                description = descriptionList.get(it),
                url = urlList.get(it)
            ))
        }

        return Result.Success(newsList)
    }

    fun getCategoryNewsList(url : String, query: List<String>) : Result<List<News>> {
        val doc : Document
        val titleList : List<String>
        val thumbnailUrlList : List<String>
        val descriptionList : List<String>
        val urlList : List<String>

        try {
            doc =
                Jsoup.connect(url).maxBodySize(0).get()
            titleList = doc.select(query.get(0)).select("img").eachAttr("alt")
            thumbnailUrlList = doc.select(query.get(0)).select("img").eachAttr("src")
            descriptionList = doc.select(query.get(1)).eachText()
            urlList = doc.select(query.get(0)).select("a").eachAttr("href")
        } catch ( e : Exception ) { return Result.Error(e.message!!) }

        val newsList : MutableList<News> = mutableListOf()
        repeat(titleList.size) {
            newsList.add(it, News(
                platForm = "category",
                thumbnailUrl = thumbnailUrlList.get(it),
                title = titleList.get(it),
                description = descriptionList.get(it),
                url = urlList.get(it)
            ))
        }

        return Result.Success(newsList)
    }
}