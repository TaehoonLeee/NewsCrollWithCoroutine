package com.example.newscroll

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

fun main(args : Array<String>) {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).timeout(10000).get()

//    val category = doc.select("div.cluster_head_topic_wrap")
    val category = doc.select("ul.nav").text().split(" ")
    println(category)
//    category.forEach {
//        println(it)
//    }
}

fun parsingCategory() : List<String> {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).timeout(10000).get()

    val category = doc.select("ul.nav").text().split(" ")

    return category
}

fun parsingCategoryHerf() : List<String> {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).timeout(10000).get()

    val categoryHref = doc.select("ul.nav a").eachAttr("href")

    return categoryHref
}