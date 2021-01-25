package com.example.newscroll

import org.jsoup.Jsoup

fun main(args : Array<String>) {
    val url = "https://news.daum.net/digital#1"
    val doc = Jsoup.connect(url).maxBodySize(0).get()

//    val tmp = doc.select("div.section_cate.section_headline").select("a.link_txt").eachText()
    val tmp = doc.select("div.section_cate.section_headline").select("img").eachAttr("alt")
    val img = doc.select("div.section_cate.section_headline").select("img").eachAttr("src")

    repeat(tmp.size) {
        println(tmp.get(it) + ", " + img.get(it))
    }
}

fun parsingCategory() : List<String> {
    val url = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=105"
    val doc = Jsoup.connect(url).timeout(10000).get()

    val category = doc.select("ul.nav a").textNodes().joinToString(",").split(" ,")

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

fun categoryNewsParsing() {
    val url = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=105&sid2=731"
    val doc = Jsoup.connect(url).get()

    val category = doc.select("dt.photo").select("img").eachAttr("alt")
    val photo = doc.select("dt.photo").select("img").eachAttr("src")
    val description = doc.select("span.lede").eachText()
}