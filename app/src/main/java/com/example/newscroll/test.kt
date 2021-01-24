package com.example.newscroll

import org.jsoup.Jsoup

fun main(args : Array<String>) {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).maxBodySize(0).get()

//    val category = doc.select("div.cluster_head_topic_wrap")
    val category = doc.select("li.cluster_item") - doc.select("li.cluster_item.as_line")
//    println(category)
    category.forEach {
        println(it.select("div.cluster_text_lede").text())
    }
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

fun thumbNailParsing() : List<String> {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).maxBodySize(0).get()
    val category = doc.select("li.cluster_item").select("img").eachAttr("src")

    return category
}

fun headlineParsing() {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).maxBodySize(0).get()

//    val category = doc.select("div.cluster_head_topic_wrap")
    val category = doc.select("li.cluster_item") - doc.select("li.cluster_item.as_line")
    category.forEach{
        it.select("a").text()
    }
}